package com.luan.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TaskDTO(
        @JsonProperty("_id")
        Long id,
        @NotBlank
        @NotNull
        @Size(min = 5, max = 100)
        String name,
        @NotBlank
        @NotNull
        @Size(min = 10, max = 200)
        String description,
        String expirationDate) {
}
