package ru.gb.task5.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.task5.model.Task;
import ru.gb.task5.status.TaskStatus;

public interface TaskRepository extends JpaRepository<Task,Long>{
    List <Task> findByStatus(TaskStatus status);
    
} 
