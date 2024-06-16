package com.sachin.TaskService.service;

import com.sachin.TaskService.modal.Task;
import com.sachin.TaskService.modal.TaskStatus;
import com.sachin.TaskService.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    private TaskRepository taskRepository;
    @Override
    public Task createTask(Task task, String requestRole) throws Exception {
        if(!requestRole.equals(("ROLE_ADMIN"))){
            throw new Exception("Only Admin Create Task");
        }
        task.setStatus(TaskStatus.PENDING);
        task.setCreatedAt(LocalDateTime.now());
        return taskRepository.save(task);
    }

    @Override
    public Task getTaskById(Long id) throws Exception {
        return taskRepository.findById(id).orElseThrow(()->new Exception("Task not found With Such Id:"+id));
    }

    @Override
    public List<Task> getALlTask(TaskStatus status) {
        List<Task> allTask=taskRepository.findAll();
        List<Task> filterTask=allTask.stream().filter(
                task -> status==null || task.getStatus().name().equalsIgnoreCase(status.toString())
        ).collect(Collectors.toList());
        return filterTask;
    }

    @Override
    public Task updateTask(Long id, Task updatedTask, Long userId) throws Exception {
        Task existingTask=getTaskById(id);
        if(updatedTask.getTitle()!=null){
            existingTask.setTitle(updatedTask.getTitle());
        }if(updatedTask.getImage()!=null){
            existingTask.setImage(updatedTask.getImage());
        }if(updatedTask.getDescription()!=null){
            existingTask.setDescription(updatedTask.getDescription());
        }if(updatedTask.getStatus()!=null){
            existingTask.setStatus(updatedTask.getStatus());
        }if(updatedTask.getDeadline()!=null){
            existingTask.setDeadline(updatedTask.getDeadline());
        }
        return taskRepository.save(existingTask);
    }

    @Override
    public void deleteTask(Long id)  {
        taskRepository.deleteById(id);

    }

    @Override
    public Task assignedToUser(Long userId, Long taskId) throws Exception {
        Task task=getTaskById(taskId);
        task.setAssignedUserId(userId);
        task.setStatus(TaskStatus.DONE);
        return taskRepository.save(task);
    }

    @Override
    public List<Task> assignedUserTask(Long userId, TaskStatus status) {
        List<Task> allTask=taskRepository.findByAssignedUserId(userId);
        List<Task> filteredTask=allTask.stream().filter(
                task -> status==null || task.getStatus().name().equalsIgnoreCase(status.toString())
        ).collect(Collectors.toList());
        return filteredTask;
    }

    @Override
    public Task completeTask(Long taskId) throws Exception {
        Task task=getTaskById(taskId);
        task.setStatus(TaskStatus.DONE);
        return taskRepository.save(task);
    }
}
