package com.vmo.hungnk.noteapp.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class ApiErrorDetail implements Serializable {
    protected HttpStatus httpStatus;

    protected String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    protected LocalDateTime timestamp = LocalDateTime.now();

    protected String entityName;

    protected String fieldName;

    protected Object fieldValue;
}
