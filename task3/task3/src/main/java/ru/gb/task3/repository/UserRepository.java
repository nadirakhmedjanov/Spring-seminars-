package ru.gb.task3.repository;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import ru.gb.task3.domain.User;

@Component
public class UserRepository {
    private List<User> users = new ArrayList<User>();

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
