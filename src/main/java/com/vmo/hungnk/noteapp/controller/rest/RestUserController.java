package com.vmo.hungnk.noteapp.controller.rest;

import com.vmo.hungnk.noteapp.model.request.UserRequestDTO;
import com.vmo.hungnk.noteapp.model.response.UserResponseDTO;
import com.vmo.hungnk.noteapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class RestUserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserRequestDTO requestDTO) {
        return ResponseEntity.ok(userService.createUser(requestDTO));
    }
}
