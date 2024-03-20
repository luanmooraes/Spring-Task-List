package com.luan.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @NotBlank
    @NotNull
    @Size(min = 5, max = 100)
    @Column(length = 100, nullable = false)
    private String title;

    @NotBlank
    @NotNull
    @Size(min = 10, max = 200)
    @Column(length = 200, nullable = false)
    private String description;

    private String expirationDate;
}
