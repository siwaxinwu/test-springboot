package com.roy.controller;

import com.roy.annotation.ReqLog;
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
    @ReqLog
    public Object getById(@PathVariable("id") String id) throws Exception {
        return User.builder()
                .id(id)
                .name("roy")
                .age(18)
                .birthday(new Date())
                .build();

    }
}