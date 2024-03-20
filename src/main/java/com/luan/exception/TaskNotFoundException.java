package com.luan.exception;

public class TaskNotFoundException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public TaskNotFoundException(Long id){
        super("Registro não encontrado: " + id);
    }
}
