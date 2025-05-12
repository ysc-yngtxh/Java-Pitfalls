# 指定基础镜像：仓库是java，tag是23
FROM openjdk:23
# 定义匿名数据卷。相当于数据存档点，可以有多个，方便回到想要的数据存档点
VOLUME /tmp
# 暴露服务端口
EXPOSE 8080
# 当前路径下通配符匹配的jar包改名并复制到容器里为app.jar
ADD *.jar app.jar
# 容器启动时要执行的命令 -- 启动Java命令
# 这里使用的脚本关键字 'CMD'、'ENTRYPOINT' 都能达到相同效果，但还是有差异语法的存在
# CMD        用于指定容器启动时默认执行的命令及其参数。如果 Dockerfile 中包含多个 CMD 指令，则只有最后一个会生效。如果在运行容器时提供了命令参数，它们将覆盖 CMD 中定义的默认命令。
# ENTRYPOINT 用于配置容器启动时执行的主要命令。如果 Dockerfile 中包含多个 ENTRYPOINT 指令，则只有最后一个会生效。ENTRYPOINT 与 CMD 一起使用时，CMD 的内容会作为参数传递给 ENTRYPOINT 指定的命令。
ENTRYPOINT ["java", "-jar", "/app.jar", "-Dfile.encoding=utf-8"]