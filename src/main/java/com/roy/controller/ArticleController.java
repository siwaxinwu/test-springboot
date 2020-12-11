package com.roy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.roy.entity.ArticleDTO;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * description：
 * author：dingyawu
 * date：created in 16:37 2020/11/22
 * history:
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @PostMapping("/add")
    public String add(@Valid @RequestBody ArticleDTO articleDTO, BindingResult bindingResult) throws JsonProcessingException {
        //如果有错误提示信息

        if (bindingResult.hasErrors()) {
            Map<String , String> map = new HashMap<>();
            bindingResult.getFieldErrors().forEach( (item) -> {
                String message = item.getDefaultMessage();
                String field = item.getField();
                map.put( field , message );
            } );
            //返回提示信息
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(map);
        }
        return "success";
    }
}
