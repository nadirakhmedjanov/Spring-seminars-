package ru.gb.task3.services;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gb.task3.domain.User;
import ru.gb.task3.repository.UserRepository;

@Service
public class DataProcessingService {
    
    @Autowired
    private UserRepository repository;
    public UserRepository getRepository() {
        return repository;
    }

    public List<User> sortUsersByAge(List<User> users) {
        return users.stream()
                .sorted(Comparator.comparing(User::getAge))
                .collect(Collectors.toList());
    }
 
    public List<User> filterUsersByAge(List<User> users, int age) {
        return users.stream()
                .filter(user -> user.getAge() > age)
                .collect(Collectors.toList());
    }
 
    public double calculateAverageAge(List<User> users) {
        return users.stream()
                .mapToInt(User::getAge)
                .average()
                .orElse(0);
    }
    public void addUserToList (User user) {
        repository.getUsers().add(user);
    
}

    public void addUser(User createUser) {
        
                throw new UnsupportedOperationException("Unimplemented method 'addUser'");
    }
}
