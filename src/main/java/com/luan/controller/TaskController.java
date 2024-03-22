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

/**
 * Controlador REST para operações relacionadas a tarefas.
 */
@Validated
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    /**
     * Construtor que recebe uma instância de TaskService.
     *
     * @param taskService Serviço responsável pela lógica de negócio das tarefas.
     */
    public TaskController(TaskService taskService){
        this.taskService = taskService;
    }

    /**
     * Retorna uma lista paginada de tarefas.
     *
     * @param page     Número da página (padrão é 0).
     * @param pageSize Tamanho da página (padrão é 10, máximo é 100).
     * @return Objeto TaskPageDTO representando a lista paginada de tarefas.
     */
    @GetMapping
    public @ResponseBody TaskPageDTO list(@RequestParam(defaultValue = "0") @PositiveOrZero int page,
                                          @RequestParam(defaultValue = "10") @Positive @Max(100) int pageSize){
        return taskService.list(page, pageSize);
    }

    /**
     * Cria uma nova tarefa.
     *
     * @param task Objeto TaskDTO representando a nova tarefa a ser criada.
     * @return Objeto TaskDTO representando a tarefa criada.
     */
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public TaskDTO create(@RequestBody @Valid TaskDTO task){
        return taskService.create(task);
    }

    /**
     * Retorna uma tarefa pelo seu ID.
     *
     * @param id ID da tarefa a ser buscada.
     * @return Objeto TaskDTO representando a tarefa encontrada.
     */
    @GetMapping("/{id}")
    public TaskDTO findById(@PathVariable @NotNull @Positive Long id){
        return taskService.findById(id);
    }

    /**
     * Atualiza uma tarefa existente pelo seu ID.
     *
     * @param id   ID da tarefa a ser atualizada.
     * @param task Objeto TaskDTO representando a nova versão da tarefa.
     * @return Objeto TaskDTO representando a tarefa atualizada.
     */
    @PutMapping("/{id}")
    public TaskDTO update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid @NotNull TaskDTO task){
        return taskService.update(id, task);
    }

    /**
     * Exclui uma tarefa pelo seu ID.
     *
     * @param id ID da tarefa a ser excluída.
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @Positive Long id){
        taskService.delete(id);
    }
}
