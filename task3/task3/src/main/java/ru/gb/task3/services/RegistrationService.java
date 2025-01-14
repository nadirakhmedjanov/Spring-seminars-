package ru.gb.task3.services;
import org.springframework.stereotype.Service;

import ru.gb.task3.domain.User;

@Service
public class RegistrationService {
    private DataProcessingService dataProcessingService;

    private UserService userService;
    private NotificationService notificationService;

    public Object getDataProcessingService;
    
    public RegistrationService(DataProcessingService dataProcessingService, UserService userService, NotificationService notificationService) {
        this.dataProcessingService = dataProcessingService;
        this.userService = userService;
        this.notificationService = notificationService;
    }
    public DataProcessingService getDataProcessingService () 
        {
        return dataProcessingService;
    }
    public void processRegistration(String name, int age, String email) {
        User createUser = userService.createUser(name, age, email);
        dataProcessingService.addUser(createUser);
        notificationService.sendNotification("Sucesfull added");
    }
    
    }


