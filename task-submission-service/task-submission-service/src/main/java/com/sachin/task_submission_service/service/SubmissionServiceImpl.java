package com.sachin.task_submission_service.service;

import com.sachin.task_submission_service.modal.Submission;
import com.sachin.task_submission_service.modal.TaskDto;
import com.sachin.task_submission_service.repository.SubmissionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
public class SubmissionServiceImpl implements SubmissionService{
    @Autowired
    private SubmissionRepo submissionRepo;
    @Autowired
    private TaskService taskService;


    @Override
    public Submission submitTask(Long taskId, String githubLink, Long userId, String jwt) throws Exception {
        TaskDto taskDto = taskService.getTaskById(taskId, jwt).getBody();
        if (taskDto == null) {
            throw new Exception("Task not found with ID: " + taskId);
        }

        Submission submission = new Submission();
        submission.setTaskId(taskId);
        submission.setGithubLink(githubLink);
        submission.setUserId(userId);
        submission.setSubmissionTime(LocalDateTime.now());
        System.out.println("Saved Successfully...");
        return submissionRepo.save(submission);
    }

    @Override
    public Submission getTaskSubmissionById(String submissionId) throws Exception {
        return submissionRepo.findById(submissionId).orElseThrow(()->new Exception("Task Submission Not Found"));
    }

    @Override
    public List<Submission> getAllTaskSubmissions() {
        return submissionRepo.findAll();
    }

    @Override
    public List<Submission> getTaskSubmissionsByTaskId(Long taskId) {
        return submissionRepo.findByTaskId(taskId);
    }

    @Override
    public Submission acceptSubmission(String id, String Status) throws Exception {
        Submission sub=getTaskSubmissionById(id);
        sub.setStatus(Status);
        if(Status.equals("ACCEPT")) {
            taskService.completeTask(sub.getTaskId());
        }
        return submissionRepo.save(sub);
    }
}
