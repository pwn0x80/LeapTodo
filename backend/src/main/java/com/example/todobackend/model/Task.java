package com.example.todobackend.model;

import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "tasks")
public class Task {
    @Id
    private String todoId;

    @NotBlank(message = "Task title is required. Please provide a meaningful title.")
    @Size(max = 100, message = "To-Do title must not exceed 100 characters")
    private String title;

    @Size(max = 255, message = "Task description must not exceed 255 characters")
    private String description;

    private LocalDateTime due_date;

    @NotNull
    private Boolean completed = false;

}

