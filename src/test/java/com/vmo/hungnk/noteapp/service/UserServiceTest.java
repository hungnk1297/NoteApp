package com.vmo.hungnk.noteapp.service;

import com.vmo.hungnk.noteapp.entity.NoteUser;
import com.vmo.hungnk.noteapp.exception.InvalidCredentialException;
import com.vmo.hungnk.noteapp.model.request.UserRequestDTO;
import com.vmo.hungnk.noteapp.model.response.UserResponseDTO;
import com.vmo.hungnk.noteapp.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepo;

    @Test
    @DisplayName("Test findUserById Success")
    void testFindById() {
        // Setup our mock repository
        NoteUser john = NoteUser.builder()
                .userID(1L).username("John").encryptedPassword("abc")
                .build();
        doReturn(Optional.of(john)).when(userRepo).findById(1L);

        // Execute the service call
        Optional<NoteUser> returnedUser = userService.findById(1L);

        // Assert the response
        assertTrue(returnedUser.isPresent(), "User was not found");
        assertSame(returnedUser.get(), john, "The User returned was not the same as the mock");
    }

    @Test
    @DisplayName("Test createUser Success")
    void testCreateUser() {
        NoteUser john = NoteUser.builder()
                .userID(1L).username("John").encryptedPassword("abc").build();

        when(userRepo.save(any())).thenReturn(john);

        UserRequestDTO userRequest = UserRequestDTO.builder()
                .username("John").password("abc")
                .build();
        UserResponseDTO userResponse = userService.createUser(userRequest);

        assertNotNull(userResponse);
        assertSame(john.getUsername(), userResponse.getUsername());
    }

    @Test
    @DisplayName("Test validateLogin throws InvalidCredentialException")
    void testValidateLogin() {
        NoteUser john = NoteUser.builder()
                .userID(1L).username("John").encryptedPassword("abc").build();

        when(userRepo.getUserByUsernameAndDeletedIsFalse(any())).thenReturn(Optional.of(john));

        UserRequestDTO userRequest = UserRequestDTO.builder()
                .username("John").password("abc")
                .build();

        assertThrows(InvalidCredentialException.class, () -> userService.validateLogin(userRequest));
    }
}
