package ru.gb.task2.model;

import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class User {
    private int id;
    private String firstName;
    private String lastName;
    
public User() {
           }
  
    // Метод equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        User user = (User) obj;
        return id == user.id && Objects.equals(firstName, user.getFirstName()) && Objects.equals(lastName, user.getLastName()); 
    }

    // Метод hashCode
    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName);
    }
}




