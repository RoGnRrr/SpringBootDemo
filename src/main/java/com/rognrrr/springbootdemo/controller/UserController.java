package com.rognrrr.springbootdemo.controller;

import com.rognrrr.springbootdemo.dao.UserMapper;
import com.rognrrr.springbootdemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserMapper userMapper;

    @GetMapping("/userList")
    public String userList(Model model, String time) {
        ArrayList<User> users = userMapper.getUserList();
        model.addAttribute("users", users);
        return "/user_list_demo";
    }

}
