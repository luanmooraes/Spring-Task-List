package com.luan.controller;

import com.luan.dto.TaskDTO;
import com.luan.service.TaskService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    @GetMapping
    public @ResponseBody List<TaskDTO> list(){
        return taskService.list();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public TaskDTO create(@RequestBody @Valid TaskDTO task){
        return taskService.create(task);
    }

    @GetMapping("/{id}")
    public TaskDTO findById(@PathVariable @NotNull @Positive Long id){
        return taskService.findById(id);
    }

    @PutMapping("/{id}")
    public TaskDTO update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid @NotNull TaskDTO task){
        return taskService.update(id, task);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id){
        taskService.delete(id);
    }



}


