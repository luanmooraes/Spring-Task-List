package com.luan.service;

import com.luan.dto.TaskDTO;
import com.luan.dto.TaskPageDTO;
import com.luan.exception.TaskNotFoundException;
import com.luan.model.Task;
import com.luan.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testList() {
        // Mocking
        Page<Task> mockPage = mock(Page.class);
        when(mockPage.get()).thenReturn((Stream<Task>) Collections.singletonList(new Task()));
        when(mockPage.getTotalElements()).thenReturn(1L);
        when(mockPage.getTotalPages()).thenReturn(1);

        when(taskRepository.findAll(PageRequest.of(0, 10))).thenReturn(mockPage);

        // Test
        TaskPageDTO result = taskService.list(0, 10);

        // Assertions
        assertNotNull(result);
        assertEquals(1, result.getTasks().size());
        assertEquals(1L, result.getTotalElements());
        assertEquals(1, result.getTotalPages());
    }

    @Test
    void testCreate() {
        // Test data

        LocalDate expirationDate = LocalDate.of(2024, 3, 25);
        TaskDTO taskDTO = new TaskDTO(1L, "Comprar mantimentos", "Comprar frutas, legumes e pão para a semana", expirationDate);
        //TaskDTO taskDTO = new TaskDTO();

        // Mocking
        when(taskRepository.save(any(Task.class))).thenReturn(new Task());

        // Test
        TaskDTO result = taskService.create(taskDTO);

        // Assertions
        assertNotNull(result);
        assertEquals(taskDTO, result);
        //assertEquals(taskDTO, result);
    }

    @Test
    void testFindById() {
        // Test data
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Test Task");

        // Mocking
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        // Test
        TaskDTO result = taskService.findById(1L);

        // Assertions
        assertNotNull(result);
        assertEquals(task.getId(), result.getId());
        assertEquals(task.getTitle(), result.getTitle());
    }

    @Test
    void testFindByIdNotFound() {
        // Mocking
        when(taskRepository.findById(1L)).thenReturn(Optional.empty());

        // Assertions
        assertThrows(TaskNotFoundException.class, () -> taskService.findById(1L));
    }

    // Tests for update and delete methods can be written similarly
}
