package com.example.todobackend.service;

import com.example.todobackend.model.Task;
import com.example.todobackend.repository.TaskRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskHandler implements TaskService {
    private TaskRepository taskRepository;

    @Autowired
    public TaskHandler(TaskRepository repository) {
        this.taskRepository = repository;
    }

    @Override
    public Task addTask(Task task) {
        try {
            var t = UUID.randomUUID().toString().split("-")[0];
            task.setTodoId(t);
            return taskRepository.save(task);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Validation failed: " + e.getMessage(), e);
        }
    }

    @Override
    public Task getTaskByTaskId(String todoId) {
        Optional<Task> optionalTask = taskRepository.findById(todoId);
        return optionalTask.orElseThrow(() -> new EntityNotFoundException("Task not found with ID: " + todoId));
    }

    @Override
    public List<Task> getPendingTasks() {
        LocalDateTime currentDate = LocalDateTime.now();
        return taskRepository.findPendingTasksAfterDate(currentDate);
    }

    @Override
    public List<Task> getCompletedTasks() {
        return taskRepository.findCompletedTasks();
    }

    @Override
    public List<Task> getOverdueTasks() {
        LocalDateTime currentDate = LocalDateTime.now();
        return taskRepository.findOverDueTasks(currentDate);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Task updateTask(Task task) {
        Task existingTask = taskRepository.findById(task.getTodoId()).orElseThrow(() -> new EntityNotFoundException("Task not found"));
        existingTask.setTitle(task.getTitle());
        existingTask.setDescription(task.getDescription());
        existingTask.setCompleted(task.getCompleted());
        existingTask.setDue_date(task.getDue_date());
        return taskRepository.save(existingTask);
    }

    public boolean deleteTask(String taskId) {
        taskRepository.findById(taskId).orElseThrow(() -> new EntityNotFoundException("Task with ID " + taskId + " not found"));
        taskRepository.deleteById(taskId);
        return true;

    }
}