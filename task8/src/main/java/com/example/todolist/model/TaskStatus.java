package com.example.todolist.model;


public enum TaskStatus {
    NOT_STARTED("не начата"),
    IN_PROGRESS("в процессе"),
    COMPLETED("завершена");

    private String displayValue;

    
    TaskStatus(String displayValue) {
        this.displayValue = displayValue;
    }

    
    public String getDisplayValue() {
        return displayValue;
    }
}