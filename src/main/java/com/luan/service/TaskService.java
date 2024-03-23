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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Classe de serviço para gerenciar tarefas.
 */
@Validated
@Service
public class TaskService {
    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    /**
     * Construtor para TaskService.
     * @param taskRepository O repositório para tarefas.
     * @param taskMapper O mapeador para converter entre entidades Task e DTOs.
     */
    public TaskService(TaskRepository taskRepository,
                       TaskMapper taskMapper){
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    /**
     * Recupera uma página de tarefas.
     * @param page O número da página (baseado em 0).
     * @param pageSize O tamanho da página.
     * @return Um objeto TaskPageDTO contendo a lista de tarefas e informações de paginação.
     */
    public TaskPageDTO list(@PositiveOrZero int page, @Positive @Max(100) int pageSize){
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by("expirationDate").ascending());
        Page<Task> pageTask = taskRepository.findAll(pageable);
        List<TaskDTO> tasks = pageTask.get().map(taskMapper::toDTO).collect(Collectors.toList());
        return new TaskPageDTO(tasks, pageTask.getTotalElements(), pageTask.getTotalPages());
    }

    /**
     * Cria uma nova tarefa.
     * @param task O objeto TaskDTO representando a tarefa a ser criada.
     * @return A tarefa criada como um objeto TaskDTO.
     */
    public TaskDTO create(@Valid @NotNull TaskDTO task){
        return taskMapper.toDTO(taskRepository.save(taskMapper.toEntity(task)));
    }

    /**
     * Recupera uma tarefa pelo seu ID.
     * @param id O ID da tarefa a ser recuperada.
     * @return A tarefa como um objeto TaskDTO.
     * @throws TaskNotFoundException se a tarefa com o ID especificado não for encontrada.
     */
    public TaskDTO findById(@NotNull @Positive Long id){
        return taskRepository.findById(id)
                .map(taskMapper::toDTO)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    /**
     * Atualiza uma tarefa existente.
     * @param id O ID da tarefa a ser atualizada.
     * @param task O objeto TaskDTO atualizado.
     * @return A tarefa atualizada como um objeto TaskDTO.
     * @throws TaskNotFoundException se a tarefa com o ID especificado não for encontrada.
     */
    public TaskDTO update(@NotNull @Positive Long id, @Valid @NotNull TaskDTO task){
        return taskRepository.findById(id)
                .map(itemFound -> {
                    itemFound.setTitle(task.title());
                    itemFound.setDescription(task.description());
                    return taskMapper.toDTO(taskRepository.save(itemFound));
                }).orElseThrow(() -> new TaskNotFoundException(id));
    }

    /**
     * Exclui uma tarefa pelo seu ID.
     * @param id O ID da tarefa a ser excluída.
     * @throws TaskNotFoundException se a tarefa com o ID especificado não for encontrada.
     */
    public void delete(@NotNull @Positive Long id){
        taskRepository.findById(id)
                .map(itemFound -> {
                    taskRepository.deleteById(id);
                    return true;
                })
                .orElseThrow(() -> new TaskNotFoundException(id));
    }
}
