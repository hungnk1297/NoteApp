package com.vmo.hungnk.noteapp.service.impl;

import com.vmo.hungnk.noteapp.constant.NoteType;
import com.vmo.hungnk.noteapp.entity.Note;
import com.vmo.hungnk.noteapp.entity.NoteCheckbox;
import com.vmo.hungnk.noteapp.entity.NoteUser;
import com.vmo.hungnk.noteapp.model.request.NoteCheckboxRequestDTO;
import com.vmo.hungnk.noteapp.model.request.NoteRequestDTO;
import com.vmo.hungnk.noteapp.model.response.NoteCheckboxResponseDTO;
import com.vmo.hungnk.noteapp.model.response.NoteResponseDTO;
import com.vmo.hungnk.noteapp.repository.NoteCheckboxRepository;
import com.vmo.hungnk.noteapp.repository.NoteRepository;
import com.vmo.hungnk.noteapp.service.NoteImageService;
import com.vmo.hungnk.noteapp.service.NoteService;
import com.vmo.hungnk.noteapp.util.ExceptionUtil;
import com.vmo.hungnk.noteapp.util.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoteServiceImpl implements NoteService {

    //    Repositories
    private final NoteRepository noteRepo;
    private final NoteCheckboxRepository checkboxRepo;

    //    Services
    private final NoteImageService noteImageService;

    //      Utils
    private final Validator validator;

    @Override
    public List<NoteResponseDTO> findAllNotesByUser(Long userID) {
        NoteUser noteUser = validator.validateUser(userID);

        return noteUser.getNotes().stream().map(note -> toNoteResponseDTO(note, userID, false))
                .collect(Collectors.toList());
    }

    @Override
    public NoteResponseDTO getNoteDetail(Long userID, Long noteID) {
        Note note = validator.validateNoteAuthorization(userID, noteID);
        return toNoteResponseDTO(note, userID, true);
    }

    @Transactional
    @Override
    public NoteResponseDTO saveNote(Long userID, NoteRequestDTO noteRequestDTO) {
        validator.validateUser(userID);
        Note savingNote = noteRequestDTO.getNoteID() != null ? validator.validateNote(noteRequestDTO.getNoteID())
                : new Note();

        savingNote.setNoteType(NoteType.findByString(noteRequestDTO.getNoteType()));
        savingNote.setCompleted(noteRequestDTO.isCompleted());
        savingNote.setTitle(noteRequestDTO.getTitle());
        savingNote.setTextDescription(noteRequestDTO.getTextDescription());
        savingNote.setLastModified(LocalDateTime.now());

        // Save image in case of Note type IMAGE
        if (noteRequestDTO.getImage() != null && savingNote.getImage() != null)
            noteImageService.saveImage(userID, savingNote, noteRequestDTO.getImage());

        // Save checkboxes in case of Note type MULTI CHECKBOXES
        if (!noteRequestDTO.getCheckboxRequestDTOS().isEmpty())
            saveCheckboxes(savingNote, noteRequestDTO.getCheckboxRequestDTOS());

        noteRepo.save(savingNote);
        return toNoteResponseDTO(savingNote, userID, true);
    }

    @Transactional
    @Override
    public NoteResponseDTO toggleCompleteNote(Long userID, Long noteID) {
        validator.validateNoteAuthorization(userID, noteID);
        Note togglingNote = validator.validateNote(noteID);
        togglingNote.setCompleted(!togglingNote.isCompleted());
        return toNoteResponseDTO(togglingNote, userID, false);
    }

    @Transactional
    @Override
    public NoteResponseDTO deleteNote(Long userID, Long noteID) {
        validator.validateNoteAuthorization(userID, noteID);
        Note deletingNote = validator.validateNote(noteID);
        deletingNote.setDeleted(!deletingNote.isDeleted());
        return toNoteResponseDTO(deletingNote, userID, false);
    }

    @Override
    public Long countUncompletedNotes(Long userID) {
        return noteRepo.countUncompletedNotes(userID);
    }

    //    Add-ons
    private void saveCheckboxes(Note note, Set<NoteCheckboxRequestDTO> checkboxRequestDTOS) {
        var checkboxSet = new HashSet<NoteCheckbox>();
        checkboxRequestDTOS.forEach(checkboxRequest -> {
            NoteCheckbox currentCheckbox = checkboxRequest.getNoteCheckBoxID() != null
                    ? validateCheckBox(checkboxRequest.getNoteCheckBoxID()) : new NoteCheckbox();
            currentCheckbox.setNote(note);
            currentCheckbox.setChecked(checkboxRequest.isChecked());
            currentCheckbox.setContent(checkboxRequest.getContent());
            checkboxSet.add(currentCheckbox);
        });
        note.setCheckBoxes(checkboxSet);
    }

    private NoteCheckbox validateCheckBox(Long checkboxID) {
        return checkboxRepo.getNoteCheckboxByNoteCheckBoxIDAndDeletedIsFalse(checkboxID)
                .orElseThrow(() -> ExceptionUtil.entityNotFound("NoteCheckbox", "checkboxID", checkboxID));
    }

    private NoteResponseDTO toNoteResponseDTO(Note note, Long userID, boolean gettingDetail) {
        NoteResponseDTO response = NoteResponseDTO.builder()
                .noteID(note.getNoteID())
                .userID(userID)
                .noteType(note.getNoteType().toString())
                .title(note.getTitle())
                .textDescription(note.getTextDescription())
                .completed(note.isCompleted())
                .build();

        if (gettingDetail) {
            // Image
            if (note.getNoteType() == NoteType.IMAGE && note.getImage() != null) {
                response.setNoteID(note.getImage().getNoteImageID());
                response.setNoteImageURL(note.getImage().getUrl());
                response.setNoteImageBase64(""); //TODO
            }

            // Multi Checkbox
            if (note.getNoteType() == NoteType.MULTI_OPTION && !note.getCheckBoxes().isEmpty()) {
                response.setCheckboxes(note.getCheckBoxes().stream().map
                        (checkbox -> toNoteCheckboxResponseDTO(checkbox, note.getNoteID()))
                        .collect(Collectors.toSet()));
            }
        }
        return response;
    }

    private NoteCheckboxResponseDTO toNoteCheckboxResponseDTO(NoteCheckbox checkbox, Long noteID) {
        return NoteCheckboxResponseDTO.builder()
                .noteID(noteID)
                .noteCheckBoxID(checkbox.getNoteCheckBoxID())
                .content(checkbox.getContent())
                .checked(checkbox.isChecked())
                .build();
    }

}
