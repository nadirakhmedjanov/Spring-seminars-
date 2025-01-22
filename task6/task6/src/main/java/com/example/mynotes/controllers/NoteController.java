package com.example.mynotes.controllers;

import com.example.mynotes.models.Note;
import com.example.mynotes.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService service;

    /**
     * Получить все заметки.
     *
     * @return список всех заметок
     */
    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        return ResponseEntity.ok(service.getAllNotes());
    }

    /**
     * Получить заметку по идентификатору.
     *
     * @param id идентификатор заметки
     * @return заметка с указанным идентификатором
     */
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getNoteById(id));
    }
  
    /**
     * Создать новую заметку.
     *
     * @param note новая заметка
     * @return созданная заметка
     */
    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        return ResponseEntity.ok(service.createNote(note));
    }

    /**
     * Обновить существующую заметку.
     *
     * @param note обновленная заметка
     * @return обновленная заметка
     */
    @PutMapping
    public ResponseEntity<Note> updateNote(@RequestBody Note note) {
        return ResponseEntity.ok(service.updateNote(note));
    }

    /**
     * Удалить заметку по идентификатору.
     *
     * @param id идентификатор заметки
     * @return ответ без содержимого
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        service.deleteNote(id);
        return ResponseEntity.ok().build();
    }

}



