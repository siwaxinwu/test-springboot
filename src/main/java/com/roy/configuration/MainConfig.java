package com.roy.configuration;

import com.roy.condition.*;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * description：
 * 可以调配4、5、6几个condition的顺序，然后决定加载不加载这个配置类
 * author：dingyawu
 * date：created in 15:32 2020/11/22
 * history:
 */
@Configuration
@Conditional({Condition4.class, Condition5.class, Condition6.class})
public class MainConfig {

}
