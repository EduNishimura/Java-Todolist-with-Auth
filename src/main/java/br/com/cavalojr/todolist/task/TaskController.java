package br.com.cavalojr.todolist.task;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cavalojr.todolist.utils.Utils;

import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

// The TaskController class is a REST controller that handles HTTP requests related to tasks.
// it is annotated with @RestController to indicate that it is a Spring MVC controller and will handle HTTP requests.
@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private ITaskRepository taskRepository;

    @PostMapping("/")
    public ResponseEntity createTask(@RequestBody TaskModel taskModel, HttpServletRequest request) {

        var userId = request.getAttribute("userId");
        taskModel.setUserId((UUID) userId);

        var currentDate = java.time.LocalDateTime.now();
        if (currentDate.isAfter(taskModel.getStartAt()) || currentDate.isAfter(taskModel.getEndAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Start date and end date cannot be in the past.");
        }

        if (taskModel.getStartAt().isAfter(taskModel.getEndAt())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Start date cannot be after end date.");
        }

        var task = this.taskRepository.save(taskModel);
        return ResponseEntity.ok(task);
    }

    @GetMapping("/")
    public List<TaskModel> getTasksByUserId(HttpServletRequest request) {
        var userId = request.getAttribute("userId");
        var task = this.taskRepository.findByUserId((UUID) userId);
        return task;
    }

    @PutMapping("/{id}")
    public ResponseEntity putMethodName(@PathVariable UUID id, @RequestBody TaskModel taskModel,
            HttpServletRequest request) {

        var task = this.taskRepository.findById(id).orElse(null);

        if (task == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Task not found");
        }

        var userId = request.getAttribute("userId");
        if (!task.getUserId().equals(userId)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User has no permission to update this task");

        }

        Utils.copyNonNullProperties(taskModel, task);
        var taskUpdated = this.taskRepository.save(task);
        return ResponseEntity.ok().body(this.taskRepository.save(taskUpdated));
    }

}
