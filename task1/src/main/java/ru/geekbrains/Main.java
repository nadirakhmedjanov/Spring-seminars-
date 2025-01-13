package ru.geekbrains;

import com.google.gson.Gson;

public class Main {
    public static void main(String[] args) {
        Gson gson = new Gson();

        // Создаём объект Person
        Person person = new Person("Ivan", "Ivanov", 25);

        // Сериализация в JSON
        String json = gson.toJson(person);
        System.out.println("Serialized JSON: " + json);

        // Десериализация из JSON
        Person deserializedPerson = gson.fromJson(json, Person.class);
        System.out.println("Deserialized Object: " + deserializedPerson);
    }
}
