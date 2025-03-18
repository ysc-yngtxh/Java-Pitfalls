package com.example.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 游家纨绔
 * @dateTime 2024-10-03 21:27
 * @apiNote TODO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    /**
     * 用户名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 性别
     */
    private String sex;

    /**
     * 地址
     */
    private String address;

}
