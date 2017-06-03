package com.mrlimrli.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mrlimrli.entities.User;
import com.mrlimrli.services.UserService;

public class UserTest {
	public static void main(String[] args) throws Exception {
		@SuppressWarnings("resource")
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:spring.xml");
		UserService userService = (UserService) ac.getBean("userService");
		User user = new User();
		user.setUsername("lijun");
		userService.add(user);
	}
	
}
