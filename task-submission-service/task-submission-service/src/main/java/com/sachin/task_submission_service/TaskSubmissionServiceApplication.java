package com.sachin.task_submission_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class TaskSubmissionServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaskSubmissionServiceApplication.class, args);
		System.out.println("Task submission Service Running...");
	}

}
