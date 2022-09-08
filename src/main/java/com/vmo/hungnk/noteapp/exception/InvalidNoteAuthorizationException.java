package com.vmo.hungnk.noteapp.exception;

public class InvalidNoteAuthorizationException extends CustomException{

    public InvalidNoteAuthorizationException(ApiErrorDetail apiErrorDetail) {
        super(apiErrorDetail);
    }
}
