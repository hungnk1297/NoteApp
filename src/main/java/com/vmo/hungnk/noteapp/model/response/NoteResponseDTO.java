package com.vmo.hungnk.noteapp.model.response;

import java.io.Serializable;
import java.util.Set;
import lombok.Builder;
import lombok.Setter;

@Setter
@Builder
public class NoteResponseDTO implements Serializable {

    private long userID;

    private long noteID;

    private String title;

    private String textDescription;

    private String noteType;

    private boolean completed;

//    Image
    private String noteImageID;
    private String noteImageURL;
    private String noteImageBase64;

//    Checkboxes
    private Set<NoteCheckboxResponseDTO> checkboxes;

}
