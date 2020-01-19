package com.tasklist.api.resources;

import com.tasklist.api.Service.TaskService;
import com.tasklist.api.model.Task;
import com.tasklist.api.model.dto.TaskDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/tasks")
public class TaskResource {

    private final TaskService taskService;

    @Autowired
    public TaskResource(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<Iterable<Task>> listAll() {
        return ResponseEntity.ok(this.taskService.findAll());
    }

    @GetMapping(path = "/{id}/status")
    public void changeStatus(@PathVariable Long id) throws Exception {
        this.taskService.changeStatus(id);
    }

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody TaskDTO taskDTO) {
        this.taskService.save(taskDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateTask(@RequestBody TaskDTO taskDTO) {
        this.taskService.updateTask(taskDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(name = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.taskService.delete(id);
        return ResponseEntity.ok().build();
    }
}
