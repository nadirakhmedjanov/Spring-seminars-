package ru.gb.task3.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ru.gb.task3.domain.User;
import ru.gb.task3.services.DataProcessingService;

@RestController
@RequestMapping("/tasks")
public class TaskController {
    @Autowired
    private DataProcessingService service;

    @GetMapping
    public List<String> getAllTasks() {
        List<String> tasks = new ArrayList<>();
        tasks.add("sort");
        tasks.add("filter");
        tasks.add("calc");
        return tasks;
        }
    @GetMapping ("/sort")
    public List<User> sortUsersByAge (){
        return service.sortUsersByAge(service.getRepository().getUsers());
        }
    @GetMapping ("/filter/{age}")
    public List<User> filterUsersByAge(@PathVariable("age") int age) {
        List<User> users = service.getRepository().getUsers();
        return service.filterUsersByAge(users, age);
        }
    @GetMapping("/calc")
    public double calculateAverageAge() {
        List<User> users = service.getRepository().getUsers();
        return service.calculateAverageAge(users);
        }
    }

