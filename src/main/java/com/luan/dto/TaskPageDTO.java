package com.luan.dto;

import java.util.List;

public class TaskPageDTO {
    private List<TaskDTO> tasks;
    private long totalElements;
    private int totalPages;

    public TaskPageDTO(List<TaskDTO> tasks, long totalElements, int totalPages) {
        this.tasks = tasks;
        this.totalElements = totalElements;
        this.totalPages = totalPages;
    }

    public List<TaskDTO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDTO> tasks) {
        this.tasks = tasks;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    @Override
    public String toString() {
        return "TaskPageDTO{" +
                "tasks=" + tasks +
                ", totalElements=" + totalElements +
                ", totalPages=" + totalPages +
                '}';
    }
}
