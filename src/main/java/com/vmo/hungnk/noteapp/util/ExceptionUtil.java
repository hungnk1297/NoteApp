package com.vmo.hungnk.noteapp.util;

import com.vmo.hungnk.noteapp.exception.ApiErrorDetail;
import com.vmo.hungnk.noteapp.exception.CustomException;
import com.vmo.hungnk.noteapp.exception.EntityNotFoundException;
import com.vmo.hungnk.noteapp.exception.ImageException;
import com.vmo.hungnk.noteapp.exception.InvalidCredentialException;
import com.vmo.hungnk.noteapp.exception.InvalidImageAuthorizationException;
import com.vmo.hungnk.noteapp.exception.InvalidNoteAuthorizationException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExceptionUtil {

    public static EntityNotFoundException entityNotFound(String entityName, String fieldName, Object fieldValue) {
        return new EntityNotFoundException(
                ApiErrorDetail.builder()
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .entityName(entityName)
                        .fieldName(fieldName)
                        .fieldValue(fieldValue)
                        .message(String.format("The requested %s with field %s is %s is NOT found!", entityName, fieldName, fieldValue))
                        .build());
    }

    public static InvalidNoteAuthorizationException invalidNoteAuthorization(String username) {
        return new InvalidNoteAuthorizationException(
                ApiErrorDetail.builder()
                        .httpStatus(HttpStatus.UNAUTHORIZED)
                        .message(String.format("The user %s doesn't have right to access the requested note", username))
                        .build());
    }

    public static InvalidImageAuthorizationException invalidImageNoteAuthorization(Long noteID, Long noteImageID) {
        return new InvalidImageAuthorizationException(
                ApiErrorDetail.builder()
                        .httpStatus(HttpStatus.UNAUTHORIZED)
                        .message(String.format("The note %s doesn't have right to access the requested image with ID %s", noteID, noteImageID))
                        .build()
        );
    }

    public static ImageException errorCreatingDirectory(String fieldName, Object fieldValue) {
        return new ImageException(ApiErrorDetail.builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                .message("Error while creating upload directory")
                .fieldName(fieldName)
                .fieldValue(fieldValue)
                .build());
    }

    public static ImageException errorUploadingImage(String fieldName, Object fieldValue) {
        return new ImageException(
                ApiErrorDetail.builder()
                        .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                        .message("Error while uploading image")
                        .fieldName(fieldName)
                        .fieldValue(fieldValue)
                        .build()
        );
    }

    public static ImageException errorDeletingImage(String fieldName, Object fieldValue) {
        return new ImageException(
                ApiErrorDetail.builder()
                        .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                        .message("Error while deleting image")
                        .fieldName(fieldName)
                        .fieldValue(fieldValue)
                        .build()
        );
    }

    public static InvalidCredentialException invalidCredential(String username) {
        return new InvalidCredentialException(
                ApiErrorDetail.builder()
                        .httpStatus(HttpStatus.BAD_REQUEST)
                        .message(String.format("Invalid Credential with username %s", username))
                        .build()
        );
    }
}
