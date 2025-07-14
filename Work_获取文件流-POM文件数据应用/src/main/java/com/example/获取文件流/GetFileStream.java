package com.example.获取文件流;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

/**
 * @author 游家纨绔
 * @dateTime 2024-06-12 06:00
 * @apiNote TODO
 */
public class GetFileStream {

	/**
	 * 总结：就是你想获得资源文件，你得从最终生成的 .class 文件为着手点，不要以 .java 文件的路径为出发点，
	 * 因为真正使用的就是.class，不会拿个.java 文件就使用。
	 */

	// TODO 通过 ClassLoader类加载器 获取资源，默认是从 classpath 根下获取。
	@Test
	public void test1() throws IOException {
		// ClassLoader类加载器，默认是从 target/classpath 根下获取。无需以“/”开头
		URL url1 = GetFileStream.class.getClassLoader().getResource("");
		System.out.println(url1);

		// info.txt 文件编译后在 classpath 根下的子目录中，因此需要将 info.txt 文件目录路径传入
		URL url2 = GetFileStream.class
				  .getClassLoader().getResource("com/example/获取文件流/info.txt");
		System.out.println(url2);
		System.out.println(URLDecoder.decode(url2.getPath(), StandardCharsets.UTF_8));

		// 而 application.yml 文件编译后是在target/classes 目录下的，因此无需以“/"开头
		InputStream inputStream = GetFileStream.class
				.getClassLoader().getResourceAsStream("application.yml");
		readerFileStream(inputStream);
	}


	// TODO 通过 Class类 获取资源，默认的是从该类所在包下取资源。
	//  查看源码可以发现：通过 Class类 获取资源，实际上还是通过 ClassLoader 类加载器来获取资源。
	//                 只是在获取资源之前先解析了 Class类 文件路径，因此获取的是 Class 文件所在包路径下的资源。
	//  ⚠️：获取当前类所在包路径下的资源，如果是非.java文件资源，那么需要在 pom.xml 文件中进行资源配置，使其加载资源。
	@Test
	public void test2() throws URISyntaxException, IOException {
		// info.txt 文件编译后在当前类所在包下，因此无需以“/”开头
		URL url1 = GetFileStream.class.getResource("info.txt");
		System.out.println(url1.toURI().getPath());


		// getResource("/") 返回类路径中第一个匹配的根目录，即 target/classes 目录
		URL url2 = GetFileStream.class.getResource("/");
		System.out.println(url2.toURI().getPath());

		// application.yml 文件在 resources 包中，编译后是直接在 target/classes 目录下的.
		// 与当前类非同一层级包下，因此获取此类文件需要以“/”开头
		URL url3 = GetFileStream.class.getResource("/application.yml");
		System.out.println(url3.toURI().getPath());

		InputStream inputStream = GetFileStream.class.getResourceAsStream("info.txt");
		readerFileStream(inputStream);
	}


	// TODO 其他写法
	@Test
	public void test3() throws URISyntaxException {
		// 使用系统的路径分隔符，进行组装路径
		String path = Thread.currentThread().getContextClassLoader().getResource("").toURI().getPath();
		path.replace(File.separator, "\\");

		// 如果资源路径存在中文，直接获取，中文部分会乱码。如果操作系统或 JVM 的默认字符不支持这些中文字符，就可能出现找不到文件的异常
		File getFilePath = new File(this.getClass().getResource("/").getFile());
		File toUriPath = new File(this.getClass().getResource("/").toURI());
	}


	// TODO Spring框架提供了更加好用的加载读取文件的方式：ClassPathResource
	@Test
	public void test4() throws IOException {
		// 默认读取 classpath 根下的资源：通过类路径创建resource
		ClassPathResource resource = new ClassPathResource("application.yml");
		PropertiesLoad(resource, "spring.application.name");

		// 重载方法：通过类路径和给定的Class类创建resource
		ClassPathResource resource1 = new ClassPathResource("jdbc.properties", Jdbc.class);
		PropertiesLoad(resource1, "user.name");

		// TODO ClassPathResource 的坑:
		//  获取文件对象的 getFile()方法，这里有一个小坑。当利用这个方法去读取 Jar 包里面类路径的文件的时候是行不通的。
		//  经过代码调试，发现加载的地址是一个 Jar 文件协议地址，它类似这种格式 jar:file:/xxx/xx.jar!/xxx。
		//  getFile()方法只支持 JBoss 的 vfs 协议头和传统文件系统的 file 协议头。
		//  而我们获取资源路径不就是为了使用资源嘛，所以推荐直接使用 getInputStream() 获取资源流。

		System.out.println(resource.getFile());
		InputStream inputStream = resource.getInputStream();
	}

	private void PropertiesLoad(ClassPathResource resource, String key) throws IOException {
		Properties ps = new Properties();
		// 这里需要将字节流转为字符流读取，避免中文乱码
		ps.load(new InputStreamReader(resource.getInputStream()));
		System.out.println(ps.getProperty(key));
		System.out.println(resource.getFile().getAbsolutePath());
	}

	private void readerFileStream(InputStream inputStream) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String readLine;
		while ((readLine = bufferedReader.readLine()) != null) {
			System.out.println(readLine);
		}
	}
}
