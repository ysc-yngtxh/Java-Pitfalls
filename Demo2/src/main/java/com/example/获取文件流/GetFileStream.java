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

    private static void readerFileStream(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String readLine;
        while ((readLine = bufferedReader.readLine()) != null) {
            System.out.println(readLine);
        }
    }
}
