package com.sachin.task_submission_service.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sub")
public class HomeController {
    @GetMapping()
    private ResponseEntity<String> submission(){
        return new ResponseEntity<>("Response From Submission microService", HttpStatus.OK);
    }
}
