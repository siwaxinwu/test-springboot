package com.roy.configuration;

import com.roy.filter.CrosFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

/**
 * description：Spring Boot如何配置Filter
 * author：dingyawu
 * date：created in 1:02 2020/11/22
 * history:
 */
//@Configuration
public class FilterConfig {
    /**
     * 注入crosFilter
     * @return
     */

    @Autowired
    private CrosFilter crosFilter;

    @Bean
    public FilterRegistrationBean crosFilter() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(crosFilter);
        registration.addUrlPatterns("/*");
        registration.setName("crosFilter");
        //设置优先级别
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registration;
    }
}