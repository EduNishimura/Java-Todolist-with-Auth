package br.com.cavalojr.todolist.task;

import lombok.Data;
import java.util.UUID;

@Data
public class TaskResponseDTO {
    private UUID id;
    private String title;
    private String description;
    private java.time.LocalDateTime startAt;
    private java.time.LocalDateTime endAt;
    private String priority;
}