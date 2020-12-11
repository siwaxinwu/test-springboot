package com.roy.configuration;

import com.roy.entity.UserInfo;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description：
 * author：dingyawu
 * date：created in 22:27 2020/11/21
 * history:
 */
@Configuration
public class AutoConfiguration {


//    @ConfigurationProperties(prefix = "userinfo")
//    @Bean
    public UserInfo userInfo(){
        return new UserInfo();
    }
}
