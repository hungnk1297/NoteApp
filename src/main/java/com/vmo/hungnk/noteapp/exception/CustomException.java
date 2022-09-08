package com.vmo.hungnk.noteapp.exception;

import java.io.Serializable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public abstract class CustomException extends RuntimeException implements Serializable {

    protected final ApiErrorDetail apiErrorDetail;


}
