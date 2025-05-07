package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    public static Map<String, Object> getObjectMap() {
        // 定义Map
        Map<String, Object> map = Map.of(
                "id", 1,
                "spuId", 0,
                "title", "title_0ba6a947909c",
                "images", "images_6b98cafc18ae",
                "price", 0,
                "indexes", "indexes_90d01ac7a320",
                "ownSpec", "ownSpec_5478c6bbcabc",
                "enable", 0,
                "createTime", "2024-04-27 00:02:10",
                "lastUpdateTime", "2024-04-27 00:02:10 789");
        Map<String, Object> map1 = Map.of(
                "id", 2,
                "spuId", 0,
                "title", "title_0ba6a947909c",
                "images", "images_6b98cafc18ae",
                "price", 0,
                "indexes", "indexes_90d01ac7a320",
                "ownSpec", "ownSpec_5478c6bbcabc",
                "enable", 0,
                "createTime", "2024-04-28 00:02:10",
                "lastUpdateTime", "2024-04-28 00:02:10 789");
        Map<String, Object> map2 = Map.of(
                "id", 3,
                "spuId", 0,
                "title", "title_0ba6a947909c",
                "images", "images_6b98cafc18ae",
                "price", 0,
                "indexes", "indexes_90d01ac7a320",
                "ownSpec", "ownSpec_5478c6bbcabc",
                "enable", 0,
                "createTime", "2024-04-29 00:02:10",
                "lastUpdateTime", "2024-04-29 00:02:10 789");
        // 将Map转为LinkedHashMap
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>(map);
        LinkedHashMap<String, Object> linkedHashMap1 = new LinkedHashMap<>(map1);
        LinkedHashMap<String, Object> linkedHashMap2 = new LinkedHashMap<>(map2);
        // 使用List包裹LinkedHashMap
        List<LinkedHashMap<String, Object>> mapList = List.of(linkedHashMap, linkedHashMap1, linkedHashMap2);
        Map<String, Object> mapObj = Map.of("result", mapList);
        return mapObj;
    }

}
