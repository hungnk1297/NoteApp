package com.vmo.hungnk.noteapp.service;

import com.vmo.hungnk.noteapp.entity.Note;
import com.vmo.hungnk.noteapp.model.request.NoteCheckboxRequestDTO;

import java.util.Set;

public interface NoteCheckboxService {
    Note saveCheckboxes(Note note, Set<NoteCheckboxRequestDTO> checkboxRequestDTOSet);


}
