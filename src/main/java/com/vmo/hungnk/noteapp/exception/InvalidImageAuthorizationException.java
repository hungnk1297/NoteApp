package com.vmo.hungnk.noteapp.exception;

public class InvalidImageAuthorizationException extends CustomException{

    public InvalidImageAuthorizationException(ApiErrorDetail apiErrorDetail) {
        super(apiErrorDetail);
    }
}
