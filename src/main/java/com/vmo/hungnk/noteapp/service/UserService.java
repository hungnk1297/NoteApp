package com.vmo.hungnk.noteapp.service;

import com.vmo.hungnk.noteapp.entity.NoteUser;
import com.vmo.hungnk.noteapp.model.request.UserRequestDTO;
import com.vmo.hungnk.noteapp.model.response.UserResponseDTO;

import java.util.Optional;

public interface UserService {

    UserResponseDTO createUser(UserRequestDTO requestDTO);

    UserResponseDTO validateLogin(UserRequestDTO requestDTO);

    boolean toggleUser(Long userID);

    Optional<NoteUser> findById(Long id);
}
