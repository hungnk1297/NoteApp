package com.vmo.hungnk.noteapp.exception;

public class EntityNotFoundException extends CustomException{

    public EntityNotFoundException(ApiErrorDetail apiErrorDetail) {
        super(apiErrorDetail);
    }
}
