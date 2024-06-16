package com.sachin.task_submission_service.modal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {
    private Long id;
    private String title;
    private Long assignedUserId;
    private String description;
    private String image;
    private List<String> tags=new ArrayList<>();
    private TaskStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime deadline;
}
