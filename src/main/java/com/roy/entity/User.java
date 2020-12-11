package com.roy.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * description：
 * author：dingyawu
 * date：created in 23:34 2020/11/21
 * history:
 */
@Data
@ToString
@Builder
public class User {
    private String id;
    private String name;
    private Integer age;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date birthday;
}
