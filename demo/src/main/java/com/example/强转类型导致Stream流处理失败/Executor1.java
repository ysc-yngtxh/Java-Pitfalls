package com.example.强转类型导致Stream流处理失败;

import com.example.强转类型导致Stream流处理失败.vo.TbSkuVo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-26 下午11:51
 * @apiNote TODO 错误强转
 */
public class Executor1 {

    private static List<LinkedHashMap<String, Object>> getLinkedHashMapList() {
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
                "lastUpdateTime", "2024-04-28 00:02:10 789");
        Map<String, Object> map1 = Map.of(
                "id", 1,
                "spuId", 0,
                "title", "title_0ba6a947909c",
                "images", "images_6b98cafc18ae",
                "price", 0,
                "indexes", "indexes_90d01ac7a320",
                "ownSpec", "ownSpec_5478c6bbcabc",
                "enable", 0,
                "createTime", "2024-04-27 00:02:10",
                "lastUpdateTime", "2024-04-29 00:02:10 789");
        Map<String, Object> map2 = Map.of(
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
        // 将Map转为LinkedHashMap
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>(map);
        LinkedHashMap<String, Object> linkedHashMap1 = new LinkedHashMap<>(map1);
        LinkedHashMap<String, Object> linkedHashMap2 = new LinkedHashMap<>(map2);
        // 使用List包裹LinkedHashMap
        return List.of(linkedHashMap, linkedHashMap1, linkedHashMap2);
    }

    public static void main(String[] args) {
        // 构造一个List<LinkedHashMap<String, Object>>
        List<LinkedHashMap<String, Object>> list = getLinkedHashMapList();
        // 再使用Map包裹着List
        Map<String, Object> mapObj = Map.of("result", list);

        // TODO 强转：从最外层的Map中获取元素，因为定义的是Object类型，所以可以强制转换成List<TbSkuVo>
        List<TbSkuVo> tbSkuVoList = (List<TbSkuVo>) mapObj.get("result");

        System.out.println(tbSkuVoList);

        // 这时候我想处理数据。比如：将 lastUpdateTime 属性值的毫秒值截取掉，只保留日期和时分秒的部分
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        tbSkuVoList.stream().map(tbSkuVo -> {
            // 解析日期时间字符串为LocalDateTime对象
            LocalDateTime dateTime = LocalDateTime.parse(tbSkuVo.getLastUpdateTime(), parser);
            String lastUpdateTimeFormat = dateTime.format(formatter);
            // 获取到lastUpdateTime属性值，并将其强制转换成String类型
            tbSkuVo.setLastUpdateTime(lastUpdateTimeFormat);
            return tbSkuVo;
        }).toList();

        System.out.println(tbSkuVoList);

        // TODO 分析：在Java中，将List<LinkedHashMap>强制转换为List<User>并不会改变列表中元素的类型。
        //           LinkedHashMap和User是两个完全不同的类型，即使你进行了强制类型转换，运行时类型仍然是LinkedHashMap。
        //  这意味着当你尝试调用TbSkuVo::getLastUpdateTime方法时，实际上你是在尝试对LinkedHashMap对象调用这个方法，
        //  而LinkedHashMap并没有这个方法，因此会报错java.lang.ClassCastException(class转换异常)。
    }
}
