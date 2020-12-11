package com.roy.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * description：
 * author：dingyawu
 * date：created in 22:08 2020/11/21
 * history:
 */
@Component
@ConfigurationProperties(prefix = "userinfo1")
@Data
@ToString
public class UserInfo1 {
    private String name;
    private Integer age;
    private Boolean active;
    private Map<String,Object> map;
    private Date createdDate;
    private List<String> hobbies;
}