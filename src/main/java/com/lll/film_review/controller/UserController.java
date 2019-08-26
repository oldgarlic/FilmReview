package com.lll.film_review.controller;

import com.lll.film_review.pojo.UserInfo;
import com.lll.film_review.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("hi")
    public String hello() {
        userService.save(new UserInfo());
        return "你好啊";
    }
}
