package com.luan.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

@Data
@Entity
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @NotBlank(message = "O titulo é obrigatório")
    @NotNull
    @Size(min = 5, max = 100)
    @Column(length = 100, nullable = false)
    private String title;

    @NotBlank(message = "A descrição é obrigatória")
    @NotNull
    @Size(min = 10, max = 200)
    @Column(length = 200, nullable = false)
    private String description;

    @NotNull
    @Past
    private LocalDate expirationDate;
}
