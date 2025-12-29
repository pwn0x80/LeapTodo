package com.example.todobackend.repository;

import com.example.todobackend.model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public interface TaskRepository extends MongoRepository<Task, String> {
    @Query("{'completed': false, 'due_date': {$gt: ?0}}")
    List<Task> findPendingTasksAfterDate(LocalDateTime date);

    @Query("{'completed': true}")
    List<Task> findCompletedTasks();

    @Query("{'completed': false,'due_date': {$lt: ?0}}")
    List<Task> findOverDueTasks(LocalDateTime date);
}