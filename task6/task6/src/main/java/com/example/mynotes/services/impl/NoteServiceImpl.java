package com.example.mynotes.services.impl;

import com.example.mynotes.exceptions.NoteNotFoundException;
import com.example.mynotes.models.Note;
import com.example.mynotes.repositories.NoteRepository;
import com.example.mynotes.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;



@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {
    private final NoteRepository repository;

    /**
     * Получить все заметки.
     *
     * @return список всех заметок
     */
    @Override
    public List<Note> getAllNotes() {
        return repository.findAll();
    }

    /**
     * Получить заметку по идентификатору.
     *
     * @param id идентификатор заметки
     * @return заметка с указанным идентификатором
     * @throws NoteNotFoundException если заметка не найдена
     */
    @Override
    public Note getNoteById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoteNotFoundException("Note not found by ID: " + id));
    }

    /**
     * Создать новую заметку.
     *
     * @param note новая заметка
     * @return созданная заметка
     */
    @Override
    public Note createNote(Note note) {
        note.setCreatedAt(LocalDateTime.now());
        return repository.save(note);
    }


    /**
     * Обновить существующую заметку.
     *
     * @param note обновленная заметка
     * @return обновленная заметка
     */
    @Override
    public Note updateNote(Note note) {
        Note existingNote = getNoteById(note.getId());
        existingNote.setTitle(note.getTitle());
        existingNote.setContent(note.getContent());
        return repository.save(existingNote);
    }

    /**
     * Удалить заметку по идентификатору.
     *
     * @param id идентификатор заметки
     */
    @Override
    public void deleteNote(Long id) {
        getNoteById(id);
        repository.deleteById(id);
    }
   
}
    
