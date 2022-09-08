package com.vmo.hungnk.noteapp.service;

import com.vmo.hungnk.noteapp.model.request.NoteRequestDTO;
import com.vmo.hungnk.noteapp.model.response.NoteResponseDTO;

import java.util.List;

public interface NoteService {

    List<NoteResponseDTO> findAllNotesByUser(Long userID);

    NoteResponseDTO getNoteDetail(Long userID, Long noteID);

    NoteResponseDTO saveNote(Long userID, NoteRequestDTO noteRequestDTO);

    NoteResponseDTO toggleCompleteNote(Long userID, Long noteID);

    NoteResponseDTO deleteNote(Long userID, Long noteID);

    Long countUncompletedNotes(Long userID);
}
