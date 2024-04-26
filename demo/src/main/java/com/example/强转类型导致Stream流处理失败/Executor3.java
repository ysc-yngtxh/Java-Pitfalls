package com.example.强转类型导致Stream流处理失败;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.DemoApplication.getLinkedHashMapList;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-26 下午11:51
 * @apiNote TODO 使用正则表达式提取字符串中的日期时间，并将其转换为指定格式
 */
public class Executor3 {

    public static void main(String[] args) {
        // 构造一个List<LinkedHashMap<String, Object>>
        List<LinkedHashMap<String, Object>> list = getLinkedHashMapList();
        // 再使用Map包裹着List
        Map<String, Object> mapObj = Map.of("result", list);

        // TODO 强转：从最外层的Map中获取元素，因为定义的是Object类型，所以可以强制转换成List<LinkedHashMap<String, Object>>
        List<LinkedHashMap<String, Object>> mapList =
                (List<LinkedHashMap<String, Object>>) mapObj.get("result");

        System.out.println("mapList：" + mapList);

        Pattern pattern = Pattern.compile("(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2})");
        List<LinkedHashMap<String, Object>> linkedHashMapList = mapList.stream().map(tbSkuVo -> {
            String originalString = String.valueOf(tbSkuVo.get("lastUpdateTime"));
            Matcher matcher = pattern.matcher(originalString);
            if (matcher.find()) {
                String dateTimeString = matcher.group(1); // 提取匹配到的组
                System.out.println(dateTimeString); // 输出: 2024-03-25 16:16:16
                tbSkuVo.put("lastUpdateTime", dateTimeString);
            }
            return tbSkuVo;
        }).toList();

        System.out.println("linkedHashMapList：" + linkedHashMapList);
    }
}
