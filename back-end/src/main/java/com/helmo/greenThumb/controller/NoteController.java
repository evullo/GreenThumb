package com.helmo.greenThumb.controller;
import com.helmo.greenThumb.model.Note;
import com.helmo.greenThumb.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = "http://localhost:5173")
public class NoteController {

    @Autowired
    private NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }


    @PostMapping
    public ResponseEntity<String> createNote(@RequestBody Note note) {
        noteService.saveNote(note);
        return ResponseEntity.status(HttpStatus.CREATED).body("La note a bien été créée");
    }

    @GetMapping
    public ResponseEntity<Iterable<Note>> getAllNotes() {
        return ResponseEntity.ok(noteService.findAll());
    }
}