package com.example.强转类型导致Stream流处理失败;

import com.alibaba.fastjson2.JSON;
import com.example.强转类型导致Stream流处理失败.vo.TbSkuVo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.example.DemoApplication.getLinkedHashMapList;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-26 下午11:51
 * @apiNote TODO 正确强转
 */
public class Executor2 {

    public static void main(String[] args) {
        // 构造一个List<LinkedHashMap<String, Object>>
        List<LinkedHashMap<String, Object>> list = getLinkedHashMapList();
        // 再使用Map包裹着List
        Map<String, Object> mapObj = Map.of("result", list);

        // TODO 强转：从最外层的Map中获取元素，因为定义的是Object类型，所以可以强制转换成List<LinkedHashMap<String, Object>>
        List<LinkedHashMap<String, Object>> mapList =
                (List<LinkedHashMap<String, Object>>) mapObj.get("result");

        System.out.println("mapList：" + mapList);

        // 利用JSON，将List中的每一个Map元素转为TbSkuVo对象
        List<TbSkuVo> tbSkuVoList = mapList.stream().map(tbSkuVo -> {
            TbSkuVo parsedObject = JSON.parseObject(JSON.toJSONString(tbSkuVo), TbSkuVo.class);
            return parsedObject;
        }).toList();

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

        System.out.println("tbSkuVoList：" + tbSkuVoList);

        // TODO 上述是利用JSON将Map转为对象后再进行修改属性值。也可以不进行转换，但要使用 tbSkuVo.get("lastUpdateTime")
        List<LinkedHashMap<String, Object>> linkedHashMapList = mapList.stream().map(tbSkuVo -> {
            try {
                String lastUpdateTime = String.valueOf(tbSkuVo.get("lastUpdateTime"));
                // 检查是否为空或null
                if (lastUpdateTime == null || lastUpdateTime.isEmpty()) {
                    // 处理空值或非法值的情况，可以返回原tbSkuVo或做其他处理
                    return tbSkuVo;
                }
                // 解析日期时间字符串为LocalDateTime对象
                LocalDateTime dateTime = LocalDateTime.parse(lastUpdateTime, parser);
                // 格式化LocalDateTime对象为不包含毫秒的字符串
                String lastUpdateTimeFormat = dateTime.format(formatter);
                // 更新tbSkuVo中的lastUpdateTime字段
                tbSkuVo.put("lastUpdateTime", lastUpdateTimeFormat);
                return tbSkuVo;
            } catch (Exception e) {
                System.out.println("解析lastUpdateTime时出错: " + e.getMessage());
                // 可以选择返回原始的tbSkuVo对象或进行其他错误处理
                return tbSkuVo;
            }
        }).toList();

        System.out.println("linkedHashMapList：" + linkedHashMapList);
    }
}
