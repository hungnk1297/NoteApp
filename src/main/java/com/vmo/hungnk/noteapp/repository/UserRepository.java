package com.vmo.hungnk.noteapp.repository;

import com.vmo.hungnk.noteapp.entity.NoteUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<NoteUser, Long> {

    Optional<NoteUser> getUserByUserIDAndDeletedIsFalse(Long userID);

    Optional<NoteUser> getUserByUsernameAndDeletedIsFalse(String username);

}
