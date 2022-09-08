package com.vmo.hungnk.noteapp.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteRequestDTO {
    private Long noteID;

    private String title;

    private String textDescription;

    private String noteType;

    private boolean completed = false;

    // For Image Note
    private MultipartFile image;

    // For Checkbox Note
    private Set<NoteCheckboxRequestDTO> checkboxRequestDTOS;
}
