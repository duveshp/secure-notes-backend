package com.learning.spring_security.services;

import com.learning.spring_security.models.Note;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface NoteService {

    public Note createNoteForUser(String username, String noteContent);

    public List<Note> getAllNotesForUser(String username);

    public  Note getNoteById(Long noteId);

    public Note updateNoteForUser(String username, Long noteId, String noteContent);

    public void deleteNoteForUser(String username, Long noteId);

}
