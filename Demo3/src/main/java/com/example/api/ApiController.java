package com.example.api;

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
    public String api() {
        return "api";
    }
}
