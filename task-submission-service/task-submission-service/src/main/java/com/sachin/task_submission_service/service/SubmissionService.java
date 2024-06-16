package com.sachin.task_submission_service.service;

import com.sachin.task_submission_service.modal.Submission;

import java.util.List;

public interface SubmissionService {
    Submission submitTask(Long taskId,String githubLink,Long userId,String jwt) throws Exception;
    Submission getTaskSubmissionById(String submissionId) throws Exception;
    List<Submission> getAllTaskSubmissions();
    List<Submission> getTaskSubmissionsByTaskId(Long taskId);
    Submission acceptSubmission(String id,String Status) throws Exception;
}
