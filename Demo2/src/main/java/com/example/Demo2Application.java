package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo2Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo2Application.class, args);
    }
    // Demo2Application.class.getResourceAsStreaAsStream ()path 以“”/开头，因为默认的是此类所在包下取资源
    // Demo2Application.class.getClassLoader().getResourceAsStreaAsStream(）不能以“/”开头，因为默认是从 classpath 根下获取，最终由 ClassLoader 获取资源
}
