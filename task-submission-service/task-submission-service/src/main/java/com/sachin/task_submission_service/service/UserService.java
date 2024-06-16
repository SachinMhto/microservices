package com.sachin.task_submission_service.service;


import com.sachin.task_submission_service.modal.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "USER-SERVICE",url = "http://localhost:8081")
public interface UserService {
    @GetMapping("/api/user")
   public UserDto getUserProfile(@RequestHeader("Authorization") String jwt);
}
