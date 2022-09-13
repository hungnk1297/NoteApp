package com.vmo.hungnk.noteapp.service.impl;

import com.vmo.hungnk.noteapp.entity.NoteUser;
import com.vmo.hungnk.noteapp.model.request.UserRequestDTO;
import com.vmo.hungnk.noteapp.model.response.UserResponseDTO;
import com.vmo.hungnk.noteapp.repository.UserRepository;
import com.vmo.hungnk.noteapp.service.UserService;
import com.vmo.hungnk.noteapp.util.ExceptionUtil;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public UserResponseDTO createUser(UserRequestDTO requestDTO) {
        NoteUser noteUser = NoteUser.builder()
            .username(requestDTO.getUsername())
            .encryptedPassword(generateEncryptedPassword(requestDTO.getUsername(),
                requestDTO.getPassword()))
            .build();

        noteUser = userRepo.save(noteUser);
        return modelMapper.map(noteUser, UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO validateLogin(UserRequestDTO requestDTO) {
        NoteUser loggingInNoteUser = userRepo.getUserByUsernameAndDeletedIsFalse(
                requestDTO.getUsername())
            .orElseThrow(() -> ExceptionUtil.invalidCredential(requestDTO.getUsername()));

        //  Validate password
        if (!loggingInNoteUser.getEncryptedPassword().equals(
            generateEncryptedPassword(requestDTO.getUsername(), requestDTO.getPassword()))) {
            throw ExceptionUtil.invalidCredential(requestDTO.getUsername());
        }

        return modelMapper.map(loggingInNoteUser, UserResponseDTO.class);
    }

    @Transactional
    @Override
    public boolean toggleUser(Long userID) {
        NoteUser noteUser = userRepo.getUserByUserIDAndDeletedIsFalse(userID)
            .orElseThrow();
        noteUser.setDeleted(!noteUser.isDeleted());
        userRepo.save(noteUser);
        return true;
    }

    @Override
    public Optional<NoteUser> findById(Long id) {
        return userRepo.findById(id);
    }

    //    Add-on
    private String generateEncryptedPassword(String username, String password) {
        String appendedString = password.concat(username);
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            byte[] digestBytes = md.digest(appendedString.getBytes());
            BigInteger no = new BigInteger(digestBytes);
            return no.toString(16);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
