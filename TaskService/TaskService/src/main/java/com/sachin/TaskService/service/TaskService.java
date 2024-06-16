package com.sachin.TaskService.service;

import com.sachin.TaskService.modal.Task;
import com.sachin.TaskService.modal.TaskStatus;

import java.util.List;

public interface TaskService {
    Task createTask(Task task,String requestRole) throws Exception;
    Task getTaskById(Long id) throws Exception;
    List<Task> getALlTask(TaskStatus status);
    Task updateTask(Long id,Task updatedTask,Long userId) throws Exception;
    void deleteTask(Long id);
    Task assignedToUser(Long userId,Long taskId)throws Exception;
    List<Task> assignedUserTask(Long userId,TaskStatus status);
    Task completeTask(Long taskId) throws Exception;
}
