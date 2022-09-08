package com.vmo.hungnk.noteapp.exception;

public class InvalidCredentialException extends CustomException{

    public InvalidCredentialException(ApiErrorDetail apiErrorDetail) {
        super(apiErrorDetail);
    }
}
