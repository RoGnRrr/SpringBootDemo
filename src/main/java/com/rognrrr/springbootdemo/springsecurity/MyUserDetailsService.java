package com.rognrrr.springbootdemo.springsecurity;

import com.rognrrr.springbootdemo.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.util.AutoPopulatingList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        try {

            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("userEmail", username);

            com.rognrrr.springbootdemo.entity.User loginUser = userMapper.getUserByEmail(paramMap);

            authorities.add(new SimpleGrantedAuthority("ADMIN"));
            User user = new User(loginUser.getEmail(), loginUser.getPassword(), authorities);

            return user;

        } catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

}
