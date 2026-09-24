package br.com.cavalojr.todolist.task;

import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public TaskDTO toDTO(TaskModel taskModel) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTitle(taskModel.getTitle());
        taskDTO.setDescription(taskModel.getDescription());
        taskDTO.setStartAt(taskModel.getStartAt());
        taskDTO.setEndAt(taskModel.getEndAt());
        taskDTO.setPriority(taskModel.getPriority());
        return taskDTO;
    }

    public TaskModel toModel(TaskDTO taskDTO) {
        TaskModel taskModel = new TaskModel();
        try {
            taskModel.setTitle(taskDTO.getTitle());
        } catch (Exception exception) {
            throw new IllegalArgumentException("Invalid task title", exception);
        }
        taskModel.setDescription(taskDTO.getDescription());
        taskModel.setStartAt(taskDTO.getStartAt());
        taskModel.setEndAt(taskDTO.getEndAt());
        taskModel.setPriority(taskDTO.getPriority());
        return taskModel;
    }

    public TaskResponseDTO toResponseDTO(TaskModel taskModel) {
        TaskResponseDTO taskResponseDTO = new TaskResponseDTO();
        taskResponseDTO.setId(taskModel.getId());
        taskResponseDTO.setTitle(taskModel.getTitle());
        taskResponseDTO.setDescription(taskModel.getDescription());
        taskResponseDTO.setStartAt(taskModel.getStartAt());
        taskResponseDTO.setEndAt(taskModel.getEndAt());
        taskResponseDTO.setPriority(taskModel.getPriority());
        return taskResponseDTO;
    }
}
