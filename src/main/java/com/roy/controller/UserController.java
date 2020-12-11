package com.roy.controller;

import com.roy.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * description：
 * author：dingyawu
 * date：created in 23:34 2020/11/21
 * history:
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/{id}")
    public Object getById(@PathVariable("id") String id){
        return User.builder()
                .id(id)
                .name("不才陈某")
                .age(18)
                .birthday(new Date())
                .build();
    }
}