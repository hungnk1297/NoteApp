package com.vmo.hungnk.noteapp.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteCheckboxRequestDTO {

    private Long noteCheckBoxID;

    private String content;

    private boolean checked;
}
