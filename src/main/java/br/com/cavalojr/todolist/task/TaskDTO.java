package br.com.cavalojr.todolist.task;

import java.util.UUID;

import lombok.Data;

@Data
public class TaskDTO {
    private UUID ID;
    private String title;
    private String description;
    private java.time.LocalDateTime startAt;
    private java.time.LocalDateTime endAt;
    private String priority;
}