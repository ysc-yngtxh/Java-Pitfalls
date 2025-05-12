package com.example.强转类型导致Stream流处理失败;

import com.example.强转类型导致Stream流处理失败.vo.TbSkuVo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

import static com.example.StrongConvertTypeApplication.getObjectMap;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-26 下午11:00
 * @apiNote TODO 错误强转
 */
public class Executor1 {

    public static void main(String[] args) {
        // 构造一个Map，其中的value值实际为 List<LinkedHashMap<String, Object>> 类型
        Map<String, Object> mapObj = getObjectMap();

        // TODO 强转：因为 mapObj 的value值定义的是Object类型，所以可以强制转换成 List<TbSkuVo>
        List<TbSkuVo> tbSkuVoList = (List<TbSkuVo>) mapObj.get("result");

        System.out.println(tbSkuVoList);

        // TODO 这时候我想处理数据。比如：将 lastUpdateTime 属性值的毫秒值截取掉，只保留日期和时分秒的部分
        DateTimeFormatter parser = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss SSS");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<TbSkuVo> skuVoList = tbSkuVoList.stream().peek(tbSkuVo -> {
            // 解析日期时间字符串为LocalDateTime对象
            LocalDateTime dateTime = LocalDateTime.parse(tbSkuVo.getLastUpdateTime(), parser);
            String lastUpdateTimeFormat = dateTime.format(formatter);
            // 获取到lastUpdateTime属性值，并将其强制转换成String类型
            tbSkuVo.setLastUpdateTime(lastUpdateTimeFormat);
        }).toList();

        System.out.println(tbSkuVoList);

        // TODO 分析：在Java中，将 List<LinkedHashMap> 强制转换为 List<TbSkuVo> 并不会改变列表中元素的类型。
        //           LinkedHashMap和 TbSkuVo 是两个完全不同的类型，即使你进行了强制类型转换，运行时类型仍然是 LinkedHashMap。
        //  这意味着当你尝试调用 TbSkuVo::getLastUpdateTime 方法时，实际上你是在尝试对 LinkedHashMap 对象调用这个方法，
        //  而 LinkedHashMap 并没有这个方法，因此会报错 java.lang.ClassCastException(class转换异常)。
    }
}
