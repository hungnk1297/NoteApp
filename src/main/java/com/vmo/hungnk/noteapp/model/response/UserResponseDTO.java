package com.vmo.hungnk.noteapp.model.response;

import java.io.Serializable;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserResponseDTO implements Serializable {

    private long userID;

    private String username;

    private boolean deleted;
}
