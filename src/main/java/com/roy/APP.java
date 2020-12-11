package com.roy;

import com.roy.configuration.YmlConfigFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.PropertySource;
/**
 * description：
 * 用@PropertySource这个注解引入自己定义的配置文件
 * author：dingyawu
 * date：created in 22:08 2020/11/21
 * history:
 */
@SpringBootApplication
@PropertySource(value = {"classpath:custom.properties"})
@PropertySource(value = {"classpath:custom.yml"},factory = YmlConfigFactory.class)
//@ServletComponentScan   //Servlet、Filter、Listener 可以直接通过 @WebServlet、@WebFilter、@WebListener 注解自动注册，无需其他代码。
public class APP {

	public static void main(String[] args) {
		SpringApplication.run(APP.class, args);
	}

}
