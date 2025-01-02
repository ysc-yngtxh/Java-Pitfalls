# 指定基础镜像：仓库是java，tag是23
FROM openjdk:23

# 定义匿名数据卷。相当于数据存档点，可以有多个，方便回到想要的数据存档点
VOLUME "/Users/youshicheng/Docker-Volumes/docker-self-project"

# 当前路径下通配符匹配的jar包改名并复制到容器里为app.jar
ADD docker-springboot.jar app.jar

# 暴露服务端口
EXPOSE 8080

# 容器启动时要执行的命令--启动Java命令
CMD ["java", "-jar", "/app.jar", "-Dfile.encoding=utf-8"]