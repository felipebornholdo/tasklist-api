package com.tasklist.api.Service.Mapper;

import com.tasklist.api.model.Task;
import com.tasklist.api.model.dto.TaskDTO;

import java.util.Date;

public class TaskMapper {

    public static Task toTask(TaskDTO taskDTO) {
        final Task task = new Task();
        if(taskDTO.getId() != null) {
            task.setId(taskDTO.getId());
        }
        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setActive(taskDTO.isActive());
        if (taskDTO.getId() != null) {
            task.setCreatedDate(taskDTO.getCreatedDate());
        } else {
            task.setCreatedDate(new Date());
        }
        task.setLastEdit(new Date());
        task.setExclusionDate(taskDTO.getExclusionDate());
        task.setConclusionDate(taskDTO.getConclusionDate());
        return task;
    }

    public static TaskDTO toTaskDTO(Task task) {
        final TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setTitle(task.getTitle());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setActive(task.isActive());
        taskDTO.setCreatedDate(task.getCreatedDate());
        taskDTO.setLastEdit(task.getLastEdit());
        taskDTO.setExclusionDate(task.getExclusionDate());
        taskDTO.setConclusionDate(task.getConclusionDate());
        return taskDTO;
    }
}
