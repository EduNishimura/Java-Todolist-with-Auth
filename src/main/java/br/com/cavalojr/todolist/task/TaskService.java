package br.com.cavalojr.todolist.task;

import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.cavalojr.todolist.utils.Utils;
import java.util.List;

@Service
public class TaskService {
    private final ITaskRepository taskRepository;

    public TaskService(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskModel create(TaskModel taskModel, UUID userId) {
        taskModel.setUserId(userId);

        var currentDate = java.time.LocalDateTime.now();
        if (currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())) {
            throw new IllegalArgumentException("Start date and end date cannot be in the past.");
        }

        if (taskModel.getStartAt().isAfter(taskModel.getEndAt())) {
            throw new IllegalArgumentException("Start date cannot be after end date.");
        }

        var task = this.taskRepository.save(taskModel);
        return task;
    }

    public List<TaskModel> readByUserId(UUID userId) {
        return taskRepository.findByUserId(userId);
    }

    public TaskModel update(UUID taskId, TaskModel taskModel, UUID userId) {
        var task = this.taskRepository.findById(taskId).orElse(null);

        if (task == null) {
            throw new IllegalArgumentException("Task not found");
        }

        if (!task.getUserId().equals(userId)) {
            throw new IllegalArgumentException("User has no permission to update this task");
        }

        Utils.copyNonNullProperties(taskModel, task);
        var taskUpdated = this.taskRepository.save(task);
        return taskUpdated;
    }
}
