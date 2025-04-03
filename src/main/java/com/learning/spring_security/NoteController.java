package com.learning.spring_security;

import com.learning.spring_security.models.Note;
import com.learning.spring_security.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/notes")
public class NoteController {

    @Autowired
    private NoteService noteService;

    @PostMapping
    public ResponseEntity<Note> createNoteForUser(@RequestBody Note note, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        String noteContent = note.getNoteContent();
        System.out.println("Note Content: "+noteContent);
        return ResponseEntity.ok(noteService.createNoteForUser(username,noteContent));
    }

    @GetMapping
    public ResponseEntity<List<Note>> getAllNotesForUser(@AuthenticationPrincipal UserDetails userDetails) {
        System.out.println("Code Entry :: 1 -->NoteController getAllNotesForUser");
        String username = userDetails.getUsername();

        return ResponseEntity.ok(noteService.getAllNotesForUser(username));
    }

    @GetMapping("/{noteId}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long noteId) {
        return ResponseEntity.ok(noteService.getNoteById(noteId));
    }

    @PutMapping("/{noteId}")
    public ResponseEntity<Note> updateNoteForUser(@PathVariable Long noteId, @RequestBody Note note, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        return ResponseEntity.ok(noteService.updateNoteForUser(username, noteId, note.getNoteContent()));
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<Void> deleteNoteForUser(@PathVariable Long noteId, @AuthenticationPrincipal UserDetails userDetails) {
        String username = userDetails.getUsername();
        noteService.deleteNoteForUser(username, noteId);
        return ResponseEntity.ok().build();
    }
}
