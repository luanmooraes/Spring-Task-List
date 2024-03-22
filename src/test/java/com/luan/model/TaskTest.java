package com.luan.model;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testValidTask() {
        Task task = new Task();
        task.setTitle("Compras");
        task.setDescription("Comprar frutas e pães");
        task.setExpirationDate(LocalDate.now().plusDays(1));

        assertTrue(validator.validate(task).isEmpty());
    }

    @Test
    public void testInvalidTitle() {
        Task task = new Task();
        task.setTitle("");

        assertEquals(5, validator.validate(task).size());
    }

    @Test
    public void testInvalidDescription() {
        Task task = new Task();
        task.setTitle("Compras");
        task.setDescription("Ir");

        assertEquals(2, validator.validate(task).size());
    }
    @Test
    public void testInvalidExpirationDate() {
        Task task = new Task();
        task.setTitle("Compras");
        task.setDescription("Comprar frutas e pães");
        task.setExpirationDate(LocalDate.now().minusDays(0)); // Expiration date in the past

        assertEquals(0, validator.validate(task).size());
    }
}
