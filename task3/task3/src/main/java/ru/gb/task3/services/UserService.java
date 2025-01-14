package ru.gb.task3.services;

import org.springframework.stereotype.Service;

import ru.gb.task3.domain.User;
@Service
public class UserService {
    private NotificationService notificationService;
 
    // Внедрение зависимости через конструктор
    public UserService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }
 
    public User createUser(String name, int age, String email) {
        User user = new User();
        user.setName(name);
        user.setAge(age);
        user.setEmail(email);
       
        // Отправляем уведомление о создании нового пользователя
        notificationService.notifyUser(user);
 
        return user;
    }
}


