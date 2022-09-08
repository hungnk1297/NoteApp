package com.vmo.hungnk.noteapp.model.response;

import lombok.Builder;
import lombok.Setter;

@Setter
@Builder
public class NoteCheckboxResponseDTO {

    private long noteID;

    private long noteCheckBoxID;

    private String content;

    private boolean checked;
}
