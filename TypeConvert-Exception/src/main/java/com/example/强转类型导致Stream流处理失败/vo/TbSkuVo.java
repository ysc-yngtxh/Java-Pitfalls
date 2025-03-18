package com.example.强转类型导致Stream流处理失败.vo;

import lombok.Data;

/**
 * @author 游家纨绔
 * @since 2024-04-27 00:00:28
 */
@Data
public class TbSkuVo {
    /**
     * sku id
     */
    private Long id;
    /**
     * spu id
     */
    private Long spuId;
    /**
     * 商品标题
     */
    private String title;
    /**
     * 商品的图片，多个图片以‘,’分割
     */
    private String images;
    /**
     * 销售价格，单位为分
     */
    private Long price;
    /**
     * 特有规格属性在spu属性模板中的对应下标组合
     */
    private String indexes;
    /**
     * sku的特有规格参数键值对，json格式，反序列化时请使用linkedHashMap，保证有序
     */
    private String ownSpec;
    /**
     * 是否有效，0无效，1有效
     */
    private Integer enable;
    /**
     * 添加时间
     */
    private String createTime;
    /**
     * 最后修改时间
     */
    private String lastUpdateTime;
}

