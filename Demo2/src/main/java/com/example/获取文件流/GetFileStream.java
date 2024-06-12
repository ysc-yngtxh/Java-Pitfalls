package com.example.获取文件流;

import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author 游家纨绔
 * @dateTime 2024-06-12 06:42
 * @apiNote TODO
 */
public class GetFileStream {

    @SneakyThrows
    public static void main(String[] args) {

        // TODO 通过 Class类 获取资源
        // 由于是通过 Class类 获取资源，默认的是从该类所在包下取资源。而info.txt文件编译后在当前类所在包下，因此无需以“/”开头
        URL url1 = GetFileStream.class.getResource("info.txt");
        System.out.println(url1.toURI().getPath());
        // application.properties 文件在 resources 包中，编译后是直接在 target/classes 目录下的.
        // 与当前类非同一层级包下，因此获取此类文件需要以“/”开头
        URL url2 = GetFileStream.class.getResource("/application.properties");
        System.out.println(url2.toURI().getPath());

        InputStream inputStream = GetFileStream.class.getResourceAsStream("info.txt");
        readerFileStream(inputStream);
        // 使用 getResource 方法获取的路径，并非是文件在磁盘上的真实路径，而是虚拟路径。因此需要通过流的方式获取



        // TODO 通过 ClassLoader类加载器 获取资源
        // URL url3 = GetFileStream.class.getClassLoader().getResource("/info.txt");
        // System.out.println(url3.toString());

        // 由于是通过 ClassLoader类加载器 获取资源，默认是从 classpath 根下获取。
        // 而 application.properties 文件编译后是在target/classes 目录下的，因此无需以“/”开头，
        InputStream inputStream1 = GetFileStream.class.getClassLoader().getResourceAsStream("application.properties");
        readerFileStream(inputStream1);
    }
    
    public class GetFileStream {
        // 总结一下，就是你想获得文件，你得从最终生成的.class 文件为着手点，不要以.java 文件的路径为出发点，因为真正使用的就是.class，不会拿个.java 文件就使用，因为 Java 是编译型语言嘛
            @SneakyThrows
                public static void main(String[] args) {

                            // TODO 通过 Class类 获取资源
                                    // 由于是通过 Class类 获取资源，默认的是从该类所在包下取资源。而info.txt文件编译后在当前类所在包下，因此无需以“/”开头
                                            URL url1 = GetFileStream.class.getResource("info.txt");
                                                    System.out.println(url1.toURI().getPath());
                                                            // application.properties 文件在 resources 包中，编译后是直接在 target/classes 目录下的.
                                                                    // 与当前类非同一层级包下，因此获取此类文件需要以“/”开头
                                                                            URL url2 = GetFileStream.class.getResource("/application.properties");
                                                                                    System.out.println(url2.toURI().getPath());

                                                                                            InputStream inputStream = GetFileStream.class.getResourceAsStream("info.txt");
                                                                                                    readerFileStream(inputStream);
                                                                                                            // 使用 getResource 方法获取的路径，并非是文件在磁盘上的真实路径，而是虚拟路径。因此需要通过流的方式获取



                                                                                                                    // TODO 通过 ClassLoader类加载器 获取资源
                                                                                                                            URL url3 = GetFileStream.class.getClassLoader().getResource("com/example/获取文件流/info.txt");
                                                                                                                                    System.out.println(url3.toString());

                                                                                                                                            // 由于是通过 ClassLoader类加载器 获取资源，默认是从 classpath 根下获取。
                                                                                                                                                    // 而 application.properties 文件编译后是在target/classes 目录下的，因此无需以“/”开头，
                                                                                                                                                            InputStream inputStream1 = GetFileStream.class.getClassLoader().getResourceAsStream("application.properties");
                                                                                                                                                                    readerFileStream(inputStream1);

                                                                                                                                                                    public class GetFileStream {

                                                                                                                                                                            @SneakyThrows
                                                                                                                                                                                public static void main(String[] args) {

                                                                                                                                                                                            // TODO 通过 Class类 获取资源
                                                                                                                                                                                                    // 由于是通过 Class类 获取资源，默认的是从该类所在包下取资源。而info.txt文件编译后在当前类所在包下，因此无需以“/”开头
                                                                                                                                                                                                            URL url1 = GetFileStream.class.getResource("info.txt");
                                                                                                                                                                                                                    System.out.println(url1.toURI().getPath());
                                                                                                                                                                                                                            // application.properties 文件在 resources 包中，编译后是直接在 target/classes 目录下的.
                                                                                                                                                                                                                                    // 与当前类非同一层级包下，因此获取此类文件需要以“/”开头
                                                                                                                                                                                                                                            URL url2 = GetFileStream.class.getResource("/application.properties");
                                                                                                                                                                                                                                                    System.out.println(url2.toURI().getPath());

                                                                                                                                                                                                                                                            InputStream inputStream = GetFileStream.class.getResourceAsStream("info.txt");
                                                                                                                                                                                                                                                                    readerFileStream(inputStream);
                                                                                                                                                                                                                                                                            // 使用 getResource 方法获取的路径，并非是文件在磁盘上的真实路径，而是虚拟路径。因此需要通过流的方式获取



                                                                                                                                                                                                                                                                                    // TODO 通过 ClassLoader类加载器 获取资源
                                                                                                                                                                                                                                                                                            // URL url3 = GetFileStream.class.getClassLoader().getResource("/info.txt");
                                                                                                                                                                                                                                                                                                    // System.out.println(url3.toString());

                                                                                                                                                                                                                                                                                                            // 由于是通过 ClassLoader类加载器 获取资源，默认是从 classpath 根下获取。
                                                                                                                                                                                                                                                                                                                    // 而 application.properties 文件编译后是在target/classes 目录下的，因此无需以“/”开头，
                                                                                                                                                                                                                                                                                                                            InputStream inputStream1 = GetFileStream.class.getClassLoader().getResourceAsStream("application.properties");
                                                                                                                                                                                                                                                                                                                                    readerFileStream(inputStream1);
                                                                                                                                                                                }

                                                                                                                                                                                    private static void readerFileStream(InputStream inputStream) throws IOException {
                                                                                                                                                                                                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                                                                                                                                                                                                        String readLine;
                                                                                                                                                                                                                while ((readLine = bufferedReader.readLine()) != null) {
                                                                                                                                                                                                                                System.out.println(readLine);
                                                                                                                                                                                                                }
                                                                                                                                                                                    }
                                                                                                                                                                    }

                                                                                                                                                                    // 使用系统的路径分隔符，进行组装路径
                                                                                                                                                                    String path = Thread.currentThread().getContextClassLoader().getResource().toURI().getPath()
                                                                                                                                                                    path.replace(File.separate, "＼＼")

                                                                                                                                                                    // 路径中存在中文，会不会影响
                                                                                                                                                                    new File(this.getClass().getResource("/").toURI ())
                                                                                                                                                                    new File(this.getClass().getResource("/").getFile())

                                                                                                                                                                            // Spring 框架提供了更加好用的加载读取文件的方式:ClassPathResource
                                                                                                                                                                                    // ClassPathResource 的坑:
                                                                                                                                                                                    获取文件对象的 getFile()方法，这里有一个小坑。当利用这个方法去读取 Jar 包里面类路径的文件的时候是行不通的。经过代码调试，发现加载的地址是一个 Jar 文件协议地址，它类似这种格式 jar:file:/xxx/xx.jar!/xxx。而 getFile()
                                                                                                                                                                                    方法只支持 JBoss 的 vfs 协议头和传统文件系统的 file 协议头。所以推荐使用 getInputStream()
                                                                                                                                                                                    // 默认读取 Resources 包下的文件
                                                                                                                                                                                    ClassPathResource resource = new ClassPathResource("application.properties")
                                                                                                                                                                                    ClassPathResource resource1 = new ClassPathResource("application.properties", Jdbc.class)
                                                                                                                                                                                    Properties ps = new Properties()
                                                                                                                                                                                    ps.load(resource1.getInputStream())
                                                                                                                                                                                    resource1.getFile().getAbsolutePath()
                }

                    private static void readerFileStream(InputStream inputStream) throws IOException {
                                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                                        String readLine;
                                                while ((readLine = bufferedReader.readLine()) != null) {
                                                                System.out.println(readLine);
                                                }
                    }


    }
                                                }
                    }
                                                                                                                                                                                                                }
                                                                                                                                                                                    }
                                                                                                                                                                                }
                                                                                                                                                                    }
                }
    }
}
