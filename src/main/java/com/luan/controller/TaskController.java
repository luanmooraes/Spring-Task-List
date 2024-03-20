package com.luan.controller;

import com.luan.model.Task;
import com.luan.repository.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@AllArgsConstructor
public class TaskController {

    private final TaskRepository taskRepository;
    @GetMapping
    public @ResponseBody List<Task> list(){
        return taskRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Task create(@RequestBody Task task){
        return taskRepository.save(task);
        //return ResponseEntity.status(HttpStatus.CREATED)
        //        .body(taskRepository.save(task));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> findById(@PathVariable Long id){
        return taskRepository.findById(id)
                .map(itemFound -> ResponseEntity.ok().body(itemFound))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Método para atualizar uma tarefa existente pelo ID.
     *
     * @param id    O ID da tarefa a ser atualizada.
     * @param task  Os novos dados da tarefa a serem atualizados.
     * @return      ResponseEntity contendo a tarefa atualizada no corpo da resposta.
     *              Retorna HTTP 200 OK se a tarefa é atualizada com sucesso.
     *              Retorna HTTP 404 Not Found se a tarefa com o ID fornecido não é encontrada.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable Long id, @RequestBody Task task){
        return taskRepository.findById(id)
                .map(itemFound -> {
                    itemFound.setTitle(task.getTitle());
                    itemFound.setDescription(task.getDescription());
                    Task updated = taskRepository.save(itemFound);
                    return ResponseEntity.ok().body(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        return taskRepository.findById(id)
                .map(itemFound -> {
                    taskRepository.deleteById(id);
                    return ResponseEntity.noContent().<Void>build();
                })
                .orElse(ResponseEntity.notFound().build());
    }



}


