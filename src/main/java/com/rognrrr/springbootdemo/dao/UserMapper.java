package com.rognrrr.springbootdemo.dao;

import com.rognrrr.springbootdemo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    User getUserByEmail(Map<String, Object> map);

    ArrayList<User> getUserList();

}
