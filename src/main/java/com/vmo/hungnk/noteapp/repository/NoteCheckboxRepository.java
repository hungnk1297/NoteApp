package com.vmo.hungnk.noteapp.repository;

import com.vmo.hungnk.noteapp.entity.NoteCheckbox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NoteCheckboxRepository extends JpaRepository<NoteCheckbox, Long> {

    Optional<NoteCheckbox> getNoteCheckboxByNoteCheckBoxIDAndDeletedIsFalse(Long checkboxID);
}