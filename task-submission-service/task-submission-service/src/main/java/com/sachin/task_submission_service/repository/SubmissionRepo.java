package com.sachin.task_submission_service.repository;

import com.sachin.task_submission_service.modal.Submission;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SubmissionRepo extends MongoRepository<Submission,String> {
    List<Submission> findByTaskId(Long taskId);
}
