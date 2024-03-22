package com.luan.dto.mapper;

import com.luan.dto.TaskDTO;
import com.luan.model.Task;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Classe responsável por realizar a conversão entre objetos Task e TaskDTO.
 */

@Component
public class TaskMapper {
    /**
     * Converte uma entidade Task para um DTO TaskDTO.
     *
     * @param task Entidade Task a ser convertida.
     * @return Objeto TaskDTO resultante da conversão.
     */
    public TaskDTO toDTO(Task task) {
        return new TaskDTO(task.getId(), task.getTitle(), task.getDescription(), task.getExpirationDate());
    }
    /**
     * Converte um DTO TaskDTO para uma entidade Task.
     *
     * @param taskDTO DTO TaskDTO a ser convertido.
     * @return Objeto Task resultante da conversão.
     */
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

