package com.vmo.hungnk.noteapp.constant;

import lombok.Getter;

@Getter
public enum NoteType {
    TEXT("TXT"),
    IMAGE("IMG"),
    MULTI_OPTION("MTO");

    private final String value;

    NoteType(String value) {
        this.value = value;
    }

    public static NoteType findByString(String value) {
        for (NoteType type : NoteType.values()) {
            if (type.getValue().equalsIgnoreCase(value)) {
                return type;
            }
        }
        return null;
    }
}
