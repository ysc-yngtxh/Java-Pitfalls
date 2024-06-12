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
 * @dateTime 2024-06-12 06:42
 * @apiNote TODO
 */
public class GetFileStream {

    /** 总结：就是你想获得资源文件，你得从最终生成的 .class 文件为着手点，不要以 .java 文件的路径为出发点，
     *       因为真正使用的就是.class，不会拿个.java 文件就使用。
     */

    // TODO 通过 Class类 获取资源，默认的是从该类所在包下取资源
    @Test
    public void test1() throws URISyntaxException, IOException {
        // info.txt 文件编译后在当前类所在包下，因此无需以“/”开头
        URL url1 = GetFileStream.class.getResource("info.txt");
        System.out.println( url1.toURI().getPath() );

        // application.properties 文件在 resources 包中，编译后是直接在 target/classes 目录下的.
        // 与当前类非同一层级包下，因此获取此类文件需要以“/”开头
        URL url2 = GetFileStream.class.getResource("/application.properties");
        System.out.println( url2.toURI().getPath() );

        InputStream inputStream = GetFileStream.class.getResourceAsStream("info.txt");
        readerFileStream(inputStream);
        // 使用 getResource 方法获取的路径，并非是文件在磁盘上的真实路径，而是虚拟路径。因此需要通过流的方式获取
    }


    // TODO 通过 ClassLoader类加载器 获取资源，默认是从 classpath 根下获取。
    @Test
    public void test2() throws IOException {
        // info.txt 文件编译后在 classpath 根下的子目录中，因此需要将 info.txt 文件目录路径传入
        URL url = GetFileStream.class
                  .getClassLoader()
                  .getResource("com/example/获取文件流/info.txt");
        System.out.println(url);
        System.out.println( URLDecoder.decode(url.getPath(), StandardCharsets.UTF_8) );

        // 而 application.properties 文件编译后是在target/classes 目录下的，因此无需以“/"开头
        InputStream inputStream = GetFileStream.class
                                  .getClassLoader()
                                  .getResourceAsStream("application.properties");
        readerFileStream(inputStream);
    }


    // TODO 通过 File类 获取资源，默认是从当前文件所在目录下获取。
    @Test
    public void test3() throws URISyntaxException, IOException {
        // 使用系统的路径分隔符，进行组装路径
        String path = Thread.currentThread().getContextClassLoader().getResource("").toURI().getPath();
        path.replace(File.separator, "\\");

        // 路径中存在中文，会不会影响
        new File(this.getClass().getResource("/").toURI()).getAbsoluteFile();
        new File(this.getClass().getResource("/").getFile()).getAbsoluteFile();

    }


    // TODO Spring框架提供了更加好用的加载读取文件的方式：ClassPathResource
    @Test
    public void test4() throws IOException {
        // 默认读取 classpath 根下的资源
        ClassPathResource resource = new ClassPathResource("application.properties");
        extracted(resource, "spring.application.name");

        // 重载方法：
        ClassPathResource resource1 = new ClassPathResource("jdbc.properties", Jdbc.class);
        extracted(resource1, "user.name");

        // ClassPathResource 的坑:
        // 获取文件对象的 getFile()方法，这里有一个小坑。当利用这个方法去读取 Jar 包里面类路径的文件的时候是行不通的。
        // 经过代码调试，发现加载的地址是一个 Jar 文件协议地址，它类似这种格式 jar:file:/xxx/xx.jar!/xxx。
        // 而 getFile()方法只支持 JBoss 的 vfs 协议头和传统文件系统的 file 协议头。所以推荐使用 getInputStream()

    }

    private void extracted(ClassPathResource resource, String key) throws IOException {
        Properties ps = new Properties();
        // 这里需要将字节流转为字符流读取，避免中文乱码
        ps.load(new InputStreamReader(resource.getInputStream()));
        System.out.println( ps.getProperty(key) );
        System.out.println( resource.getFile().getAbsolutePath() );
    }

    private void readerFileStream(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String readLine;
        while ((readLine = bufferedReader.readLine()) != null) {
            System.out.println(readLine);
        }
    }
}
