package com.roy;

import com.roy.entity.UserInfo;
import com.roy.entity.UserInfo1;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootInterceptApplicationTests {

	@Autowired(required = false)
	@Qualifier(value = "winP")
	private UserInfo winP;

	@Autowired(required = false)
	@Qualifier(value = "LinuxP")
	private UserInfo linP;

	@Test
	void contextLoads() {
		System.out.println(winP);
		System.out.println("-------------------");
		System.out.println(linP);
	}
}
