package ru.gb.task3.services;

import org.springframework.stereotype.Service;

import ru.gb.task3.domain.User;

@Service
public class NotificationService {
    public void notifyUser(User user) {
        System.out.println("A new user has been created: " + user.getName());
    }

    public void sendNotification(String string) {
        
        throw new UnsupportedOperationException("Unimplemented method 'sendNotification'");
    }
}
