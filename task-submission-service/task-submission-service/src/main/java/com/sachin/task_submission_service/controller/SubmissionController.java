package com.sachin.task_submission_service.controller;


import com.sachin.task_submission_service.modal.Submission;
import com.sachin.task_submission_service.modal.UserDto;
import com.sachin.task_submission_service.service.SubmissionService;
import com.sachin.task_submission_service.service.TaskService;
import com.sachin.task_submission_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/submission")
public class SubmissionController {
    @Autowired
    private SubmissionService submissionService;
    @Autowired
    private UserService userService;
    @Autowired
    private TaskService taskService;

    @PostMapping()
    public ResponseEntity<Submission> submitTask(@RequestParam Long taskId,
                                                 @RequestParam String github_link,
                                                 @RequestHeader("Authorization")String jwt) throws Exception{
        System.out.println("getting hit..");
        System.out.println(taskId);
        System.out.println(github_link);
        UserDto user=userService.getUserProfile(jwt);
        Submission submission=submissionService.submitTask(taskId,github_link,user.getId(),jwt);
        return new ResponseEntity<>(submission, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Submission> getSubmissionById(@PathVariable String id,
                                                        @RequestHeader("Authorization")String jwt) throws Exception{
        UserDto userDto=userService.getUserProfile(jwt);
        Submission submission=submissionService.getTaskSubmissionById(id);
        return new ResponseEntity<>(submission,HttpStatus.CREATED);
    }
    @GetMapping()
    public ResponseEntity<List<Submission>> getAllSubmission(
                                                             @RequestHeader("Authorization")String jwt) throws Exception{
        UserDto userDto=userService.getUserProfile(jwt);
        List<Submission> submission=submissionService.getAllTaskSubmissions();
        return new ResponseEntity<>(submission,HttpStatus.CREATED);
    }
    @GetMapping("/task/{taskId}")
    public ResponseEntity<List<Submission>> getAllSubmissionByTaskId(@PathVariable Long taskId,
                                                             @RequestHeader("Authorization")String jwt) throws Exception{
        UserDto userDto=userService.getUserProfile(jwt);
        List<Submission> submission=submissionService.getTaskSubmissionsByTaskId(taskId);
        return new ResponseEntity<>(submission,HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Submission> acceptOrDecline(@PathVariable String id,
                                                                     @RequestParam("status") String status,
                                                                     @RequestHeader("Authorization")String jwt) throws Exception{
        UserDto userDto=userService.getUserProfile(jwt);
        Submission submission=submissionService.acceptSubmission(id,status);
        return new ResponseEntity<>(submission,HttpStatus.CREATED);
    }
}
