package com.helmo.greenThumb.services;


import com.helmo.greenThumb.infrastructures.NoteRepository;
import com.helmo.greenThumb.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService  {
    private final NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }

    public List<Note> findAll() {
        return noteRepository.findAll();
    }

    public void edit(Long id, Note note) {
        Note noteToEdit = noteRepository.findById(id).orElseThrow();
        noteToEdit.setContent(note.getContent());
        noteToEdit.setTitle(note.getTitle());
        noteRepository.save(noteToEdit);
    }

    public void delete(Long id) {
        noteRepository.deleteById(id);
    }
}
