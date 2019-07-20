package com.rognrrr.springbootdemo.springsecurity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)  //  启用方法级别的权限认证
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .authorizeRequests()//配置权限
            .antMatchers("/", "/index", "/favicon.ico", "/hyper/**", "/**/**").permitAll()
            // .anyRequest().authenticated()//任意请求需要登录
            .anyRequest().permitAll()//任意请求不需要登录
            .and()
        .formLogin()//开启formLogin默认配置
            .loginPage("/login").permitAll()//请求时未登录跳转接口
            .failureUrl("/login?error=true")//用户密码错误跳转接口
            .defaultSuccessUrl("/aaa",true)//登录成功跳转接口
            .loginProcessingUrl("/j_spring_secutity_check")//post登录接口，登录验证由系统实现
            .and()
        .logout()//配置注销
            .logoutUrl("/logout")//注销接口
            .logoutSuccessUrl("/login/logout").permitAll()//注销成功跳转接口
            .deleteCookies("myCookie") //删除自定义的cookie
            .and()
        .csrf().disable();           //禁用csrf

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
    }




    @Bean
    public PasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return new BCryptPasswordEncoder();
    }

}
