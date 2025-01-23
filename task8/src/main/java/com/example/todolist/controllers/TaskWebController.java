package com.example.todolist.controllers;

import com.example.todolist.model.Task;
import com.example.todolist.model.TaskStatus;
import com.example.todolist.service.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@Controller
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskWebController {

    private final TaskService taskService;

    
    @GetMapping
    public String getAllTasks(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "taskList";
    }

    
    @GetMapping("/status/{status}")
    public String getTasksByStatus(@PathVariable TaskStatus status,
                                   Model model) {
        List<Task> tasks = taskService.getTasksByStatus(status);
        model.addAttribute("tasks", tasks);
        return "taskList";
    }

    
    @GetMapping("/add")
    public String showAddTaskForm(Model model) {
        model.addAttribute("task", new Task());
        return "addTaskForm";
    }

    
    @PostMapping("/add")
    public String addTask(@ModelAttribute("task") Task task) {
        taskService.addTask(task);
        return "redirect:/tasks";
    }

    
    @GetMapping("/{id}/edit")
    public String showEditTaskForm(@PathVariable Long id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "editTaskForm";
    }

    
    @PostMapping("/{id}/edit")
    public String updateTask(@PathVariable Long id,
                             @ModelAttribute("task") Task task) {
        taskService.updateTask(id, task);
        return "redirect:/tasks";
    }

    
    @PostMapping("/{id}/status/{status}")
    public String updateTaskStatus(@PathVariable Long id,
                                   @PathVariable TaskStatus status) {
        taskService.updateTaskStatus(id, status);
        return "redirect:/tasks";
    }

    
    @PostMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }

}
