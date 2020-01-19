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
        return task;
    }
}
