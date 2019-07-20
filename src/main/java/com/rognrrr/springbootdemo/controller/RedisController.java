package com.rognrrr.springbootdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @GetMapping("/test")
    public ModelAndView test() {
        ModelAndView model = new ModelAndView();
        return model;
    }


    @GetMapping("/userList")
    public String userList() {

        Map<String, Object> map = new HashMap<>();
        map.put("userId", 1);
        map.put("userName", "容mm");
        map.put("userRole", "admin");
        redisTemplate.opsForSet().add("key", map);
        map = new HashMap<>();
        map.put("userId", 2);
        map.put("userName", "嘉明鸽");
        map.put("userRole", "user");
        redisTemplate.opsForSet().add("key", map);

        System.out.println(redisTemplate.opsForSet().members("key"));


        return "/user_list_demo";
    }

}
