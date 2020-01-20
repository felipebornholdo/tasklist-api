package com.tasklist.api.Service;

import com.tasklist.api.Service.Mapper.TaskMapper;
import com.tasklist.api.model.Task;
import com.tasklist.api.model.dto.TaskDTO;
import com.tasklist.api.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Iterable<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task findById(Long id) {
        return this.taskRepository.findById(id).orElse(null);
    }

    public void save(TaskDTO taskDTO) throws Exception {
        try {
            Task task = TaskMapper.toTask(taskDTO);
            this.taskRepository.save(task);
        } catch (Exception e) {
            throw new Exception("Erro ao adicionar a tarefa.");
        }
    }

    public void changeStatus(Long id) throws Exception {
        try {
            Task task = findById(id);
            TaskDTO taskDTO = TaskMapper.toTaskDTO(task);
            taskDTO.setActive(!task.isActive());
            if (taskDTO.isActive()) {
                taskDTO.setConclusionDate(new Date());
            } else {
                taskDTO.setConclusionDate(null);
            }
            taskDTO.setLastEdit(new Date());
            task = TaskMapper.toTask(taskDTO);
            this.taskRepository.save(task);
        } catch (Exception e) {
            throw new Exception("Erro ao mudar status da tarefa.");
        }
    }

    public void updateTask(TaskDTO taskDTO) throws Exception {
        try {
            Task entity = findById(taskDTO.getId());
            if (Objects.nonNull(entity)) {
                Task task = TaskMapper.toTask(taskDTO);
                task.setCreatedDate(entity.getCreatedDate());
                this.taskRepository.save(task);
            }
        } catch (Exception e) {
            throw new Exception("Erro ao atualizar a tarefa.");
        }
    }

    public void delete(Long id) throws Exception {
        try {
            this.taskRepository.deleteById(id);
        } catch (Exception e) {
            throw new Exception("Erro ao deletar a tarefa");
        }
    }

}
