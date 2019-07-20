package com.rognrrr.springbootdemo.controller;

import com.rognrrr.springbootdemo.dao.UserMapper;
import com.rognrrr.springbootdemo.entity.User;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/rabbitMQ")
public class RabbitMQController {

    @Autowired private RabbitTemplate rabbitTemplate;
    @Autowired private AmqpAdmin amqpAdmin;
    @Autowired private UserMapper userMapper;

    @GetMapping("/test")
    @ResponseBody
    public String test() {


        ArrayList<User> users = userMapper.getUserList();
        Map<String, Object> map = new HashMap<>();
        map.put("userid", "1");
        map.put("username", "Rrr");

//        // direct 模式
//        rabbitTemplate.convertAndSend("user", "username", map);
//        Object object = rabbitTemplate.receiveAndConvert("username");
//        System.out.println(object.getClass());
//        System.out.println(object);


        // fanout 模式
        rabbitTemplate.convertAndSend("userall", "", users);
        // System.out.println(rabbitTemplate.receiveAndConvert("userid"));
        // System.out.println(rabbitTemplate.receiveAndConvert("username"));


        return "{}";
    }

}
