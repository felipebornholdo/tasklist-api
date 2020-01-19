package com.tasklist.api.Service;

import com.tasklist.api.Service.Mapper.TaskMapper;
import com.tasklist.api.model.Task;
import com.tasklist.api.model.dto.TaskDTO;
import com.tasklist.api.repository.TaskRepository;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

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

    public Optional<Task> findById(Long id) {
        return this.taskRepository.findById(id);
    }

    public void save(TaskDTO taskDTO) {
        Task task = TaskMapper.toTask(taskDTO);
        this.taskRepository.save(task);
    }

    public void changeStatus(Long id) throws Exception {
        Task task = new Task();
        try {
            Optional<Task> taskOptional = findById(id);
            if (taskOptional.isPresent()) {
                task = taskOptional.get();
            }
            if (task.isActive()) {
                task.setActive(false);
            } else {
                task.setActive(true);
            }
            task.setLastEdit(new Date());
            this.taskRepository.save(task);
        } catch (Exception e) {
            throw new Exception("Erro ao mudar status da Tarefa");
        }
    }

    public void updateTask(TaskDTO taskDTO) {
        Optional<Task> taskOptional = this.findById(taskDTO.getId());
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task = TaskMapper.toTask(taskDTO);
            task.setCreatedDate(taskOptional.get().getCreatedDate());
            this.taskRepository.save(task);
        }
    }

    public void delete(Long id){
        taskRepository.deleteById(id);
    }
}
