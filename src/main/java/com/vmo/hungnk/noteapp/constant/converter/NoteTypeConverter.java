package com.vmo.hungnk.noteapp.constant.converter;

import com.vmo.hungnk.noteapp.constant.NoteType;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class NoteTypeConverter implements AttributeConverter<NoteType, String> {

    @Override
    public String convertToDatabaseColumn(NoteType attribute) {
        return attribute.getValue();
    }

    @Override
    public NoteType convertToEntityAttribute(String dbData) {
        return NoteType.findByString(dbData);
    }
}
