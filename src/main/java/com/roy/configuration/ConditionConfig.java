package com.roy.configuration;

import com.roy.condition.*;
import com.roy.entity.UserInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * description：
 * author：dingyawu
 * date：created in 15:09 2020/11/22
 * history:
 */

@Configuration
@Conditional({Condition1.class, Condition2.class, Condition3.class})
public class ConditionConfig {

    /**
     * 在Windows环境下注入的Bean为winP
     *
     * @return
     */
    @Bean("winP")
    @Conditional(value = {WindowsCondition.class})
    public UserInfo personWin() {
        return new UserInfo();
    }

    /**
     * 在Linux环境下注入的Bean为LinuxP
     *
     * @return
     */
    @Bean("LinuxP")
    @Conditional(value = {LinuxCondition.class})
    public UserInfo personLinux() {
        return new UserInfo();
    }
}