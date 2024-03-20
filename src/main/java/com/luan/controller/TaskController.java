package com.luan.controller;

import com.luan.model.Task;
import com.luan.service.TaskService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public @ResponseBody List<Task> list(){
        return taskService.list();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Task create(@RequestBody @Valid Task task){
        return taskService.create(task);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> findById(@PathVariable @NotNull @Positive Long id){
        return taskService.findById(id)
                .map(itemFound -> ResponseEntity.ok().body(itemFound))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid Task task){
        return taskService.update(id, task)
                .map(itemFound -> ResponseEntity.ok().body(itemFound))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull @Positive Long id){
        if(taskService.delete(id)) {
            return ResponseEntity.noContent().<Void>build();
        }
        return ResponseEntity.noContent().<Void>build();
    }



}


