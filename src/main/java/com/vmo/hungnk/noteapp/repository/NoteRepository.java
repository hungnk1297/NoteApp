package com.vmo.hungnk.noteapp.repository;

import com.vmo.hungnk.noteapp.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    Optional<Note> getNotByNoteIDAndDeletedIsFalse(Long noteID);

    @Query("SELECT COUNT(n) FROM Note n " +
            "WHERE n.deleted = false " +
            "AND n.noteUser.userID = :userID AND n.completed = false ")
    Long countUncompletedNotes(@Param("userID") Long userID);

    @Query("SELECT n FROM Note n " +
            "WHERE n.deleted = false " +
            "AND n.noteUser.userID = :userID")
    List<Note> findAllByUserID(@Param("userID") Long userID);
}