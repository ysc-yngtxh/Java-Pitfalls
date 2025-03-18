package com.example.配置文件获取pom中数据;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 * @dateTime 2024-06-14 23:30
 * @apiNote TODO
 */
@Component
public class CustomConfig implements ApplicationRunner {

    @Value("${parent.groupId}")
    public String parentGroupId;
    @Value("${parent.artifactId}")
    public String parentArtifactId;
    @Value("${parent.version}")
    public String parentVersion;

    @Value("${custom.groupId}")
    public String customGroupId;
    @Value("${custom.artifactId}")
    public String customArtifactId;
    @Value("${custom.version}")
    public String customVersion;
    @Value("${custom.description}")
    public String customDescription;
    @Value("${custom.name}")
    public String customName;

    @Value("${custom.basedir}")
    public String customBasedir;
    @Value("${custom.baseUri}")
    public String customBaseUri;

    @Value("${custom.build.directory}")
    public String customBuildDirectory;
    @Value("${custom.build.source.directory}")
    public String customBuildSourceDirectory;

    @Value("${self.use.property}")
    public String selfUseProperty;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.err.println("parent.groupId = " + parentGroupId);
        System.err.println("parent.artifactId = " + parentArtifactId);
        System.err.println("parent.name = " + parentVersion + "\n");

        System.err.println("custom.groupId = " + customGroupId);
        System.err.println("custom.artifactId = " + customArtifactId);
        System.err.println("custom.version = " + customVersion);
        System.err.println("custom.description = " + customDescription);
        System.err.println("custom.name = " + customName + "\n");

        System.err.println("custom.basedir = " + customBasedir);
        System.err.println("custom.baseUri = " + customBaseUri + "\n");

        System.err.println("custom.build.directory = " + customBuildDirectory);
        System.err.println("custom.build.source.directory = " + customBuildSourceDirectory + "\n");

        System.err.println("self.use.property = " + selfUseProperty);
    }
}
