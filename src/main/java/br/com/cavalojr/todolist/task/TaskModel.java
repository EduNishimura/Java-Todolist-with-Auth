package br.com.cavalojr.todolist.task;

import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity(name = "tb_tasks")
public class TaskModel {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID Id;

    @Column(length = 50)
    private String title;

    private String description;

    @Column(nullable = false)
    private LocalDateTime startAt;

    @Column(nullable = false)
    private LocalDateTime endAt;

    private String priority;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private UUID userId;

    public void setTitle(String title) throws Exception {
        if (title.length() > 50) {
            throw new Exception("title cannot be longer than 50 characters");
        }
        this.title = title;
    }
}
