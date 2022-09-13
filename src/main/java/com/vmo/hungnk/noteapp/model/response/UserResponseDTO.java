package com.vmo.hungnk.noteapp.model.response;

import java.io.Serializable;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO implements Serializable {

    private long userID;
    private String username;

    private boolean deleted;
}
