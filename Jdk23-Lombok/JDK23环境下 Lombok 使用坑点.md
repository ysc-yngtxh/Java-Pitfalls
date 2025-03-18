
## 在实际使用 Jdk23 的过程中，发现了一个在使用 Lombok 时需要注意的坑点。

   <h3>
     <figure> 
       Lombok是一个Java库，它可以通过注解来减少Java代码的冗余。<br/><br/>
       Lombok系列的注解，例如@Getter、@Setter、@ToString、@EqualsAndHashCode、@Builder等，<br/><br/>
       这些注解可以在编译时自动生成getter、setter、toString、equals、hashCode以及构建器（builder）等方法，从而减少了手动编写这些方法的需要。
     </figure>   
   </h3>

## 一、坑点场景：
   <h3>
     <figure>    
       1、在 Jdk23 环境下，将对应版本的SpringBoot项目使用 Maven 的 package 进行打 Jar包，部署在 docker 中发现项目启动老是失败。
          经排查，失败原因是使用 lombok 的类并没有生成注解方法。<br/><br/>
       2、刚开始还以为是自己的打包原因，后面在使用 Idea 工具使用 Maven Clean 后，再 compile，
          发现在 target 包下的字节码文件中，也并没有生成对应的 lombok 注解的方法。<br/><br/>
       3、然而，我直接使用 Idea 启动项目后，重新编译的字节码文件就又有了 lombok 注解的方法。 <br/><br/>
       4、懵圈了
     </figure>
     <h4>
       <figure>    
         后面自己测试发现：Jdk1.8、Jdk17、Jdk21 环境下，在不启动项目的情况下使用 compile 编译后生成的字节码文件中是有生成了lombok注解方法。
         说明 Jdk23 暂时不支持在编译期间生成 lombok 注解方法
       </figure> 
     </h4>
   </h3>






## 二、解决方法
   <h3>在Maven 项目的 pom 文件中添加 编译 插件 maven-compiler-plugin，并指定编译的 JDK 版本为 17，如下：</h3>
```xml
<build>
    <plugins>
        <plugin>
            <!-- 定义了maven-compiler-plugin的版本，确保你使用的是期望的插件版本 -->
            <groupId>org.apache.maven.plugins</groupId>
            <artifactId>maven-compiler-plugin</artifactId>
            <version>3.8.1</version>
            <configuration>
                <!-- 指定Java源代码的编译版本为1.8、17、21-->
                <source>21</source>
                <!-- 指定生成的字节码版本（即类文件）的目标版本也为1.8、17、21 -->
                <target>21</target>
                <!-- 指定注解处理器的路径。注解处理器在编译时运行，用于生成额外的源代码或字节码 -->
                <annotationProcessorPaths>
                    <!-- 指定了lombok注解处理器的依赖 -->
                    <path>
                        <groupId>org.projectlombok</groupId>
                        <artifactId>lombok</artifactId>
                        <version>1.18.34</version>
                    </path>
                    <!-- 指定了mapstruct注解处理器的依赖 -->
                    <path>
                        <groupId>org.mapstruct</groupId>
                        <artifactId>mapstruct-processor</artifactId>
                        <version>1.4.2.Final</version>
                    </path>
                </annotationProcessorPaths>
            </configuration>
        </plugin>
    </plugins>
</build>
```

## 三、maven-compiler-plugin 与 spring-boot-maven-plugin 的区别
   <h3>     
     <figure>
       maven-compiler-plugin
       <figure>
         是 Maven 的一个插件，用于编译 Java 源代码。它允许你指定 Java 源代码的编译版本、生成的字节码版本以及注解处理器的路径等配置。
         它通常用于编译项目中的 Java 源代码，生成相应的字节码文件。
         简单理解就是只编译生成项目的字节码文件，不会扩展编译生成项目的依赖文件【例如：所需的 SpringBoot 依赖不会生成】
       </figure>
       spring-boot-maven-plugin
       <figure>
         是 Spring Boot 提供的一个 Maven 插件，用于构建和运行 Spring Boot 应用程序。它允许你指定 Spring Boot 应用的入口类、打包方式、运行配置等。
         它通常用于构建和运行 Spring Boot 应用程序，生成相应的可执行 Jar 文件。
       </figure>
         在实际使用中，maven-compiler-plugin 和 spring-boot-maven-plugin 可以一起使用，以实现编译和打包 Spring Boot 应用程序。
         在 Maven 的 pom 文件中，你可以同时配置这两个插件，以实现编译和打包 Spring Boot 应用程序。
     </figure>
   </h3>







