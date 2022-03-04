package com.myqa.project;

import javax.annotation.PreDestroy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.myqa.project.DB.DB;

@SpringBootApplication
public class MyQaProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyQaProjectApplication.class, args);
	}
	@PreDestroy
	public void onExit() {
		DB.getInstance().disconnectDB();
	}
}
