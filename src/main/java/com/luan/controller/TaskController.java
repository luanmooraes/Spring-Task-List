package com.luan.controller;

import com.luan.model.Task;
import com.luan.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@AllArgsConstructor
public class TaskController {

    private final TaskRepository taskRepository;
    @GetMapping("/list-tasks")
    public @ResponseBody List<Task> list(){
        return taskRepository.findAll();
    }

    @PostMapping("/register-task")
    @ResponseStatus(code = HttpStatus.CREATED)
    public Task create(@RequestBody Task task){
        return taskRepository.save(task);
        //return ResponseEntity.status(HttpStatus.CREATED)
        //        .body(taskRepository.save(task));
    }

}


