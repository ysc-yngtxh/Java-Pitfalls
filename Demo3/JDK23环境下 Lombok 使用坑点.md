
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







