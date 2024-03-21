package com.luan.service;

import com.luan.dto.TaskDTO;
import com.luan.dto.TaskPageDTO;
import com.luan.dto.mapper.TaskMapper;
import com.luan.exception.TaskNotFoundException;
import com.luan.model.Task;
import com.luan.repository.TaskRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository,
                       TaskMapper taskMapper){
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public TaskPageDTO list(@PositiveOrZero int page, @Positive @Max(100) int pageSize){
        Page<Task> pageTask = taskRepository.findAll(PageRequest.of(page, pageSize));
        List<TaskDTO> tasks = pageTask.get().map(taskMapper::toDTO).collect(Collectors.toList());
        return new TaskPageDTO(tasks, pageTask.getTotalElements(), pageTask.getTotalPages());

    }

    public TaskDTO create(@Valid @NotNull TaskDTO task){
        return taskMapper.toDTO(taskRepository.save(taskMapper.toEntity(task)));
    }

    public TaskDTO findById(@NotNull @Positive Long id){
        return taskRepository.findById(id)
                .map(task -> taskMapper.toDTO(task))
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    public TaskDTO update(@NotNull @Positive Long id, @Valid @NotNull TaskDTO task){
        return taskRepository.findById(id)
                .map(itemFound -> {
                    itemFound.setTitle(task.title());
                    itemFound.setDescription(task.description());
                    return taskMapper.toDTO(taskRepository.save(itemFound));
                }).orElseThrow(() -> new TaskNotFoundException(id));
    }

    public void delete(@NotNull @Positive Long id){
        //taskRepository.delete(taskRepository.findById(id))
        //        .orElseThrow(() -> new TaskNotFoundException(id));
        taskRepository.findById(id)
                .map(itemFound -> {
                    taskRepository.deleteById(id);
                    return true;
                })
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

}
