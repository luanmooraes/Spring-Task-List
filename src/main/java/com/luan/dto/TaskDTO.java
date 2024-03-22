package com.luan.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class TaskDTO {
        private Long id;

        @NotBlank
        @NotNull
        @Size(min = 5, max = 100)
        private String title;

        @NotBlank
        @NotNull
        @Size(min = 10, max = 200)
        private String description;

        @NotNull
        private LocalDate expirationDate;

        public TaskDTO(Long id, String title, String description, LocalDate expirationDate) {
                this.id = id;
                this.title = title;
                this.description = description;
                this.expirationDate = expirationDate;
        }

        @JsonProperty("_id")
        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getTitle() {
                return title;
        }

        public void setTitle(String title) {
                this.title = title;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public LocalDate getExpirationDate() {
                return expirationDate;
        }

        public void setExpirationDate(LocalDate expirationDate) {
                this.expirationDate = expirationDate;
        }
}
