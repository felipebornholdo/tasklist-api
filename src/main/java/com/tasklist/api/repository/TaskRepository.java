package com.tasklist.api.repository;

import com.tasklist.api.model.Task;
import org.springframework.data.repository.CrudRepository;

public interface TaskRepository extends CrudRepository<Task, Long> {

}
