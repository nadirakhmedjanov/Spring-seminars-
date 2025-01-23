package com.example.todolist.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Аннотация TrackUserAction используется для маркировки методов,
 * которые требуется отслеживать в контексте действий пользователя.
 * Данная аннотация применяется к методам и задает метаданные,
 * которые могут быть использованы аспектом для регистрации событий.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TrackUserAction {
}
