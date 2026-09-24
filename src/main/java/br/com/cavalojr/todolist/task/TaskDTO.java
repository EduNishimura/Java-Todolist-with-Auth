package br.com.cavalojr.todolist.task;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TaskDTO {
    @NotBlank
    @Size(max = 100)
    private String title;

    private String description;

    @NotNull
    private java.time.LocalDateTime startAt;

    @NotNull
    private java.time.LocalDateTime endAt;

    private String priority;
}