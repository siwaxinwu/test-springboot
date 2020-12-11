package com.roy;

import com.roy.entity.UserInfo;
import com.roy.entity.UserInfo1;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class RestSpringbootApplicationTests {

	@Autowired
	private UserInfo userInfo;

	@Autowired
	private UserInfo1 userInfo1;

	@Test
	void contextLoads() {
		System.out.println(userInfo);
	}

	/**
	 * 利用@ConfigurationProperties(prefix = "userinfo1")来注入配置文件
	 * 但配置文件不是约定大于配置的文件，而是采用
	 * @PropertySource(value = {"classpath:custom.yml"},factory = YmlConfigFactory.class)来加载的自定义配置文件
	 */
	@Test
	void contextLoads1() {
		System.out.println(userInfo1);
	}

}
