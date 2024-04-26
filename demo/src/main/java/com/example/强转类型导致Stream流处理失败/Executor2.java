package com.example.强转类型导致Stream流处理失败;

import com.alibaba.fastjson2.JSON;
import com.example.强转类型导致Stream流处理失败.vo.TbSkuVo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.DemoApplication.getObjectMap;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-26 下午11:51
 * @apiNote TODO 正确强转
 */
public class Executor2 {

    public static void main(String[] args) {
        // 构造一个Map，其中的value值实际为 List<LinkedHashMap<String, Object>> 类型
        Map<String, Object> mapObj = getObjectMap();

        // TODO 强转：因为 mapObj 的value值定义的是Object类型，所以可以强制转换成 List<LinkedHashMap<String, Object>>
        List<LinkedHashMap<String, Object>> mapList =
                (List<LinkedHashMap<String, Object>>) mapObj.get("result");

        System.out.println("mapList：" + mapList);

        // 利用JSON，将List中的每一个Map元素转为TbSkuVo对象
        List<TbSkuVo> tbSkuVoList = mapList.stream().map(tbSkuVo -> {
            return JSON.parseObject(JSON.toJSONString(tbSkuVo), TbSkuVo.class);
        }).toList();

        // TODO 这时候我想处理数据。比如：将 lastUpdateTime 属性值的毫秒值截取掉，只保留日期和时分秒的部分
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        tbSkuVoList.stream().peek(tbSkuVo -> {
            // 解析日期时间字符串为LocalDateTime对象
            LocalDateTime dateTime = LocalDateTime.parse(tbSkuVo.getLastUpdateTime(), parser);
            String lastUpdateTimeFormat = dateTime.format(formatter);
            // 获取到lastUpdateTime属性值，并将其强制转换成String类型
            tbSkuVo.setLastUpdateTime(lastUpdateTimeFormat);
        }).toList();

        System.out.println("tbSkuVoList：" + tbSkuVoList);

        // TODO 上述是利用JSON将Map转为对象后再进行修改属性值。也可以不进行转换，但要使用 tbSkuVo.get("lastUpdateTime")
        List<LinkedHashMap<String, Object>> linkedHashMapList = mapList.stream().peek(tbSkuVo -> {
            // 解析日期时间字符串为LocalDateTime对象
            LocalDateTime dateTime = LocalDateTime.parse(String.valueOf(tbSkuVo.get("lastUpdateTime")), parser);
            // 格式化LocalDateTime对象为不包含毫秒的字符串
            String lastUpdateTimeFormat = dateTime.format(formatter);
            // 更新tbSkuVo中的lastUpdateTime字段
            tbSkuVo.put("lastUpdateTime", lastUpdateTimeFormat);
        }).toList();

        System.out.println("linkedHashMapList：" + linkedHashMapList);

        // TODO 正则表达式提取日期时间
        Pattern pattern = Pattern.compile("(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2})");
        List<LinkedHashMap<String, Object>> linkedHashMapRegexList = mapList.stream().peek(tbSkuVo -> {
            String originalString = String.valueOf(tbSkuVo.get("lastUpdateTime"));
            Matcher matcher = pattern.matcher(originalString);
            if (matcher.find()) {
                // 提取匹配到的组
                String dateTimeString = matcher.group(1);
                System.out.println(dateTimeString);
                tbSkuVo.put("lastUpdateTime", dateTimeString);
            }
        }).toList();

        System.out.println("linkedHashMapRegexList：" + linkedHashMapRegexList);
    }
}
