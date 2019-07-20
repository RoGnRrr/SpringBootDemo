package com.rognrrr.springbootdemo.controller;

import com.rognrrr.springbootdemo.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/hello")
    String home() {
        return "Hello World!";
    }

}
