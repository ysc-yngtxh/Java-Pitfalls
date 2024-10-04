package com.example.api;

import com.example.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 游家纨绔
 * @dateTime 2024-10-04 18:26
 * @apiNote TODO
 */
@RestController
public class ApiController {

    @RequestMapping("/api")
    public User api() {
        return User.builder().name("游家纨绔").build();
    }
}
