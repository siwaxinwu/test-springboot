package com.roy.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * description：
 * @Component ：注入到IOC容器中
 * @ConfigurationProperties：从配置文件中读取文件
 *
 *
 * author：dingyawu
 * date：created in 22:08 2020/11/21
 * history:
 */
@Component
@ConfigurationProperties(prefix = "userinfo")
@Data
@ToString
public class UserInfo {

    @Value("${userinfo.name}")
    private String name;
    private Integer age;
    private Boolean active;
    private Map<String,Object> map;
    private Date createdDate;
    private List<String> hobbies;
}