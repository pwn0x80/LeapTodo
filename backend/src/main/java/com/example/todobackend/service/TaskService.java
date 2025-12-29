package com.example.todobackend.service;

import com.example.todobackend.model.Task;

import java.text.ParseException;
import java.util.List;

public interface TaskService {
    Task addTask(Task task);

    Task getTaskByTaskId(String todoId);

    boolean deleteTask(String taskId);

    List<Task> getPendingTasks();

    List<Task> getCompletedTasks();

    List<Task> getOverdueTasks();

    List<Task> getAllTasks();

    Task updateTask(Task task);


}