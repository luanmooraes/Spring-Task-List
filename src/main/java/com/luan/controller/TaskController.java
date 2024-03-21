package com.luan.controller;

import com.luan.dto.TaskDTO;
import com.luan.dto.TaskPageDTO;
import com.luan.service.TaskService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
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
    public @ResponseBody TaskPageDTO list(@RequestParam(defaultValue = "0") @PositiveOrZero int page,
                                          @RequestParam(defaultValue = "10") @Positive @Max(100) int pageSize){
        return taskService.list(page, pageSize);
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


