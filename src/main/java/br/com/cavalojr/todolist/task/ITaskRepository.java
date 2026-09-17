package br.com.cavalojr.todolist.task;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

// The ITaskRepository interface extends the JpaRepository interface, which provides CRUD operations for the TaskModel entity. It also defines a custom method findByUserId to retrieve tasks associated with a specific user ID.
public interface ITaskRepository extends JpaRepository<TaskModel, UUID> {
    List<TaskModel> findByUserId(UUID userId);
}
