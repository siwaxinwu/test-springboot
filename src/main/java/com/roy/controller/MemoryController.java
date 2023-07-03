package com.roy.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: dingyawu
 * @Description: TODO
 * @Date: Created in 18:03 2023/6/12
 */
@RestController
@RequestMapping(value = "/memory")
public class MemoryController {
    /**
     * 创建一个强引用的全局变量
     */
    List<byte[]> memoryList = new ArrayList<>();

    @GetMapping("/oom")
    public String oom(int n) {
        /**
         * 每次请求生成一个n M大小的空间
         */
        byte[] b = new byte[n * 1024 * 1024];
        memoryList.add(b);
        return "success";
    }
}

