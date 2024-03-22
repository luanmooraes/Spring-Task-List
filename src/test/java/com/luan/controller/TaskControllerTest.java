package com.luan.controller;

import com.luan.dto.TaskDTO;
import com.luan.dto.TaskPageDTO;
import com.luan.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        LocalDate expirationDate = LocalDate.of(2024, 3, 25);
        List<TaskDTO> tasks = new ArrayList<>();
        tasks.add(new TaskDTO(1L, "Comprar mantimentos", "Comprar frutas, legumes e pão para a semana", expirationDate));

        int pageNumber = 1;
        int totalPages = 5;

        TaskPageDTO pageDTO = new TaskPageDTO(tasks, pageNumber, totalPages);
        when(taskService.list(anyInt(), anyInt())).thenReturn(pageDTO);

        TaskPageDTO result = taskController.list(0, 10);

        assertEquals(pageDTO, result);
    }

    @Test
    void testCreate() {
        LocalDate expirationDate = LocalDate.of(2024, 3, 25);
        TaskDTO taskDTO = new TaskDTO(1L, "Comprar mantimentos", "Comprar frutas, legumes e pão para a semana", expirationDate);
        when(taskService.create(any(TaskDTO.class))).thenReturn(taskDTO);

        TaskDTO result = taskController.create(taskDTO);

        assertEquals(taskDTO, result);
    }

    @Test
    void testFindById() {
        LocalDate expirationDate = LocalDate.of(2024, 3, 25);
        TaskDTO taskDTO = new TaskDTO(1L, "Comprar mantimentos", "Comprar frutas, legumes e pão para a semana", expirationDate);
        when(taskService.findById(anyLong())).thenReturn(taskDTO);

        TaskDTO result = taskController.findById(1L);

        assertEquals(taskDTO, result);
    }

    @Test
    void testUpdate() {
        LocalDate expirationDate = LocalDate.of(2024, 3, 25);
        TaskDTO taskDTO = new TaskDTO(1L, "Comprar mantimentos", "Comprar frutas, legumes e pão para a semana", expirationDate);
        when(taskService.update(anyLong(), any(TaskDTO.class))).thenReturn(taskDTO);

        TaskDTO result = taskController.update(1L, new TaskDTO(1L, "Comprar mantimentos", "Comprar frutas, legumes e pão para a semana", expirationDate));

        assertEquals(taskDTO, result);
    }

    @Test
    void testDelete() {
        taskController.delete(1L);
    }
}
