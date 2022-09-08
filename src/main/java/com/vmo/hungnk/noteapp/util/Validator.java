package com.vmo.hungnk.noteapp.util;

import com.vmo.hungnk.noteapp.entity.Note;
import com.vmo.hungnk.noteapp.entity.NoteImage;
import com.vmo.hungnk.noteapp.entity.NoteUser;
import com.vmo.hungnk.noteapp.repository.NoteRepository;
import com.vmo.hungnk.noteapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Validator {

    private final UserRepository userRepo;
    private final NoteRepository noteRepo;

    public NoteUser validateUser(Long userID) {
        return userRepo.getUserByUserIDAndDeletedIsFalse(userID)
                .orElseThrow(() -> ExceptionUtil.entityNotFound("User", "userID", userID));
    }

    public Note validateNoteAuthorization(Long userID, Long noteID) {
        NoteUser noteUser = validateUser(userID);
        var notes = noteRepo.findAllByUserID(userID);
        return notes.stream().filter(note -> note.getNoteID().equals(noteID)).findFirst()
                .orElseThrow(() -> ExceptionUtil.invalidNoteAuthorization(noteUser.getUsername()));
    }

    public NoteImage validateNoteImage(Long userID, Long noteID, Long noteImageID) {
        Note authorizedNote = validateNoteAuthorization(userID, noteID);

        if (authorizedNote.getImage() == null || !authorizedNote.getImage().getNoteImageID().equals(noteImageID))
            throw ExceptionUtil.invalidImageNoteAuthorization(noteID, noteImageID);

        return authorizedNote.getImage();
    }

    public Note validateNote(Long noteID) {
        return noteRepo.getNotByNoteIDAndDeletedIsFalse(noteID)
                .orElseThrow(() -> ExceptionUtil.entityNotFound("Note", "noteID", noteID));
    }
}
