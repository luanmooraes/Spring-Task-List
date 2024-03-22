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
        TaskPageDTO pageDTO = new TaskPageDTO();
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
        TaskDTO taskDTO = new TaskDTO(1L, "Comprar mantimentos", "Comprar frutas, legumes e pão para a semana", "2024/03/25");
        when(taskService.update(anyLong(), any(TaskDTO.class))).thenReturn(taskDTO);

        TaskDTO result = taskController.update(1L, new TaskDTO());

        assertEquals(taskDTO, result);
    }

    @Test
    void testDelete() {
        taskController.delete(1L);

        // Verify that taskService.delete(id) was called once
    }
}
