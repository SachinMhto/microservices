package com.sachin.task_submission_service.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="submission")
public class Submission {
    @Id
    private String id;
    private Long taskId;
    private String githubLink;
    private Long userId;
    private String Status="PENDING";
    private LocalDateTime submissionTime;

}
