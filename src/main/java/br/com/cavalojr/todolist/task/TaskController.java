package br.com.cavalojr.todolist.task;

import java.util.UUID;

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
@RequestMapping("/tasks") // @RequestMapping specifies the base URL path for all the endpoints in this
                          // controller.
public class TaskController {

    private final TaskService taskService; // The TaskController class has a dependency on the TaskService class, which
                                           // is injected through the constructor. This allows the controller to access
                                           // the methods defined in the service for performing task-related operations.

    public TaskController(TaskService taskService) { // The constructor receives an instance of the TaskService class as
                                                     // a parameter and assigns it to the taskService attribute.
        this.taskService = taskService;
    }

    @PostMapping("/")
    public ResponseEntity<?> createTask(@RequestBody TaskModel taskModel, HttpServletRequest request) {
        // @RequestBody is used to bind the incoming json request body to the taskModel
        // parameter
        // HttpServletRequest is used to access the HTTP request and retrieve the userId
        // attribute set by the authentication filter.
        try {
            var userId = request.getAttribute("userId");
            var task = this.taskService.create(taskModel, (UUID) userId);
            return ResponseEntity.ok(task);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> getTasksByUserId(HttpServletRequest request) {
        try {
            var userId = request.getAttribute("userId");
            var task = this.taskService.readByUserId((UUID) userId);
            return ResponseEntity.ok(task);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putMethodName(@PathVariable UUID id, @RequestBody TaskModel taskModel,
            HttpServletRequest request) {

        try {
            var taskUpdated = this.taskService.update(id, taskModel, (UUID) request.getAttribute("userId"));
            return ResponseEntity.ok(taskUpdated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
