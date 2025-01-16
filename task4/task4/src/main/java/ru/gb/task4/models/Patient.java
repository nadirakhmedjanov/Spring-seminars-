package ru.gb.task4.models;

import java.time.LocalTime;
import lombok.Data;

@Data
public class Patient {
    private String name;
    private LocalTime appointmentTime;

}
