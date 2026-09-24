package br.com.cavalojr.todolist.task;

import java.util.UUID;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.PostMapping;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService; // TaskController tem uma dependência do TaskService, que é injetada através
                                           // do construtor. O TaskService é responsável por lidar com a lógica de
                                           // negócios relacionada às tarefas, como criação, leitura e atualização de
                                           // tarefas.
    private final TaskMapper taskMapper;

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;
    }

    @PostMapping
    public ResponseEntity<?> createTask(@Valid @RequestBody TaskDTO taskDTO, HttpServletRequest request) {
        // @RequestBody is used to bind the incoming json request body to the taskModel
        // parameter
        // HttpServletRequest is used to access the HTTP request and retrieve the userId
        // attribute set by the authentication filter.
        var userId = request.getAttribute("userId");
        var taskModel = this.taskMapper.toModel(taskDTO);
        var task = this.taskService.create(taskModel, (UUID) userId);
        var taskDTOResponse = this.taskMapper.toResponseDTO(task);
        return ResponseEntity.ok(taskDTOResponse);
    }

    @GetMapping
    public ResponseEntity<?> getTasksByUserId(HttpServletRequest request) {
        var userId = request.getAttribute("userId");
        var task = this.taskService.readByUserId((UUID) userId);
        var taskDTOlist = task.stream().map(taskModel -> this.taskMapper.toResponseDTO(taskModel)).toList();
        return ResponseEntity.ok(taskDTOlist);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putMethodName(@PathVariable UUID id, @Valid @RequestBody TaskDTO taskDTO,
            HttpServletRequest request) {

        var userId = request.getAttribute("userId");
        var taskModel = this.taskMapper.toModel(taskDTO);
        var taskUpdated = this.taskService.update(id, taskModel, (UUID) userId);
        var taskResponseDTOUpdated = this.taskMapper.toResponseDTO(taskUpdated);
        return ResponseEntity.ok(taskResponseDTOUpdated);
    }
}
