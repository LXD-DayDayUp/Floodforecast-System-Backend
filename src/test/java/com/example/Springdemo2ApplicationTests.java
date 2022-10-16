package com.example;

import com.example.Dao.UserDao;
import com.example.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Springdemo2ApplicationTests {

	@Autowired
	private UserDao userDao;

	@Test
	void contextLoads() {
		User users = userDao.selectById(1);
		System.out.println(users);
	}

}
