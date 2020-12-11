package com.roy.controller;

import com.roy.entity.RepeatSubmit;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description：标注了@RepeatSubmit注解，全部的接口都需要拦截
 * author：dingyawu
 * date：created in 0:23 2020/11/22
 * history:
 */
@RestController
@RequestMapping("/user")
@RepeatSubmit
public class LoginController {
    @RequestMapping("/login")
    public String login(){
        return "login success";
    }
}