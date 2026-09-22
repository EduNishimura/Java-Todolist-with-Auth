package br.com.cavalojr.todolist.task;

import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    public TaskDTO toDTO(TaskModel taskModel) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setID(taskModel.getID());
        taskDTO.setTitle(taskModel.getTitle());
        taskDTO.setDescription(taskModel.getDescription());
        taskDTO.setStartAt(taskModel.getStartAt());
        taskDTO.setEndAt(taskModel.getEndAt());
        taskDTO.setPriority(taskModel.getPriority());
        return taskDTO;
    }

    public TaskModel toModel(TaskDTO taskDTO) {
        TaskModel taskModel = new TaskModel();
        taskModel.setID(taskDTO.getID());
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
}
