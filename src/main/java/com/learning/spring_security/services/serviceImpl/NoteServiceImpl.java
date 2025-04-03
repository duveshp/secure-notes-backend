package com.learning.spring_security.services.serviceImpl;

import com.learning.spring_security.models.Note;
import com.learning.spring_security.repositories.NotesRepository;
import com.learning.spring_security.services.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteServiceImpl implements NoteService {

    @Autowired
    private NotesRepository notesRepository;

    @Override
    public Note createNoteForUser(String username, String noteContent) {
        Note note = new Note();
        note.setOwnerUsername(username);
        note.setNoteContent(noteContent);
        return notesRepository.save(note);
    }

    @Override
    public List<Note> getAllNotesForUser(String username) {
        System.out.println("COde Entry ::2 -> ServiceImpl getAllNotesForUser()");

        return notesRepository.findByOwnerUsername(username);
    }

    @Override
    public Note getNoteById(Long noteId) {
        return notesRepository.findById(noteId).orElseThrow(() -> new RuntimeException("Note not found"));
    }

    @Override
    public Note updateNoteForUser(String username, Long noteId, String noteContent) {
        Note note = notesRepository.findById(noteId).orElseThrow(() -> new RuntimeException("Note not found"));
        if (note != null) {
            note.setNoteContent(noteContent);
            return notesRepository.save(note);
        }
        return null;
    }

    @Override
    public void deleteNoteForUser(String username, Long noteId) {
        notesRepository.deleteById(noteId);
    }

}
