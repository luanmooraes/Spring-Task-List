package com.luan.dto.mapper;

import com.luan.dto.TaskDTO;
import com.luan.model.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public TaskDTO toDTO(Task task) {
        return new TaskDTO(task.getId(), task.getTitle(), task.getDescription(), task.getExpirationDate());
    }

    public Task toEntity(TaskDTO taskDTO) {
        if (taskDTO == null) {
            return null;
        }
        Task task = new Task();
        if (taskDTO.id() != null) {
            task.setId(taskDTO.id());
        }
        task.setTitle(taskDTO.title());
        task.setDescription(taskDTO.description());
        task.setExpirationDate(taskDTO.expirationDate());
        return task;
    }

}

