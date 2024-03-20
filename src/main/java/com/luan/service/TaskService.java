package com.luan.service;

import com.luan.exception.TaskNotFoundException;
import com.luan.model.Task;
import com.luan.repository.TaskRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Validated
@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public List<Task> list(){
        return taskRepository.findAll();
    }

    public Task create(@Valid Task task){
        return taskRepository.save(task);
    }

    public Task findById(@NotNull @Positive Long id){
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    public Task update(@NotNull @Positive Long id, @Valid Task task){
        return taskRepository.findById(id)
                .map(itemFound -> {
                    itemFound.setTitle(task.getTitle());
                    itemFound.setDescription(task.getDescription());
                    return taskRepository.save(itemFound);
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
