package com.luan.controller;

import com.luan.model.Task;
import com.luan.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("tasks")
@AllArgsConstructor
public class TaskController {

    private final TaskRepository taskRepository;
    @GetMapping
    public List<Task> list(){
        return taskRepository.findAll();
    }

}


