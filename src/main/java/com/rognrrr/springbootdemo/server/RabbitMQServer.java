package com.rognrrr.springbootdemo.server;

import com.rognrrr.springbootdemo.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RabbitMQServer {

    @RabbitListener(queues = "userid")
    public void userid(ArrayList<User> users){
        System.out.println(new Exception().getStackTrace()[0].getMethodName());
        System.out.println(users.get(0).getName());
    }

    @RabbitListener(queues = "username")
    public void username(ArrayList<User> users){
        System.out.println(new Exception().getStackTrace()[0].getMethodName());
        System.out.println(users.get(1).getName());
    }

}
