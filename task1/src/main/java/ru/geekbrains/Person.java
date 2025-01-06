package ru.geekbrains;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class Person {
    private String firstName;
    private String lastName;
    private int age;

    // Метод toString
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    // Метод equals
    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj);
    }

    // Метод hashCode
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}



