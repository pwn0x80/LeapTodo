package com.example.todobackend.controller;

import com.example.todobackend.model.Task;
import com.example.todobackend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;


    @PostMapping
    public ResponseEntity<Map<String, String>> createTask(@Valid @RequestBody Task task) {
        Task newTask = taskService.addTask(task);
        Map<String, String> res = new HashMap<>();
        res.put("message", "Successfully added a new task");
        res.put("status", "ok");
        return ResponseEntity.ok().body(res);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Map<String, String>> updateTask(@PathVariable String id, @Valid @RequestBody Task task) {
        task.setTodoId(id);
        Task newTask = taskService.updateTask(task);
        Map<String, String> res = new HashMap<>();
        res.put("message", "Successfully updated a new task");
        res.put("message", "Successfully updated a new task");
        res.put("status", "ok");
        return ResponseEntity.ok().body(res);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<Map<String, Object>> getTaskById(@PathVariable String todoId) {
        Task task = taskService.getTaskByTaskId(todoId);
        Map<String, Object> resp = new HashMap<>();
        resp.put("data", task);
        resp.put("status", "ok");
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public List<Task> getTask(@RequestParam(required = false) String type) {
        List<Task> tasks;
        if ("Completed".equalsIgnoreCase(type)) {
            tasks = taskService.getCompletedTasks();
        } else if ("inProgress".equalsIgnoreCase(type)) {
            tasks = taskService.getPendingTasks();
        } else if ("OverDue".equalsIgnoreCase(type)) {
            tasks = taskService.getOverdueTasks();
        } else if ("Todo".equalsIgnoreCase(type)) {
            tasks = taskService.getAllTasks();
        } else {
            tasks = taskService.getAllTasks();
        }
        return tasks;
    }


    @GetMapping("/pending")
    public List<Task> getPendingTasks() {
        return taskService.getPendingTasks();
    }

    @DeleteMapping("/{todoId}")
    public ResponseEntity<Map<String, String>> deleteTask(@PathVariable String todoId) {
        taskService.deleteTask(todoId);
        Map<String, String> resp = new HashMap<>();
        resp.put("message", "successfully deleted");
        resp.put("status", "ok");
        return new ResponseEntity<>(resp, HttpStatus.OK);

    }


}