package com.example.mynotes.services;

import com.example.mynotes.models.Note;

import java.util.List;

/**
 * Интерфейс сервиса для работы с заметками.
 */

public interface NoteService {
    List<Note> getAllNotes();
    Note getNoteById(Long id);
    Note createNote(Note note);
    Note updateNote(Note note);
    void deleteNote(Long id);   

}
