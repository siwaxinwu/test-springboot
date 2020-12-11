package com.roy.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * description：@WebFilter是Servlet3.0的一个注解，用于标注一个Filter，Spring Boot也是支持这种方式，
 * 只需要在自定义的Filter上标注该注解即可，
 * 要想@WebFilter注解生效，需要在配置类上标注另外一个注解@ServletComponentScan用于扫描使其生效，如下：
 *
 * 无需添加此注解@Component，在启动类添加@ServletComponentScan注解后，会自动将带有@WebFilter的注解进行注入！
 *
 *
 * author：dingyawu
 * date：created in 1:01 2020/11/22
 * history:
 */
@Component
//@WebFilter(filterName = "crosFilter",urlPatterns = {"/*"})
public class CrosFilter implements Filter {

    //重写其中的doFilter方法
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
            ServletException {
        HttpServletResponse response = (HttpServletResponse) res;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers"," Origin, X-Requested-With, Content-Type, Accept");
        //继续执行下一个过滤器
        chain.doFilter(req, response);
    }
}