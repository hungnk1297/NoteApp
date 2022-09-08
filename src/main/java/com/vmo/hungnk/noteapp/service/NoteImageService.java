package com.vmo.hungnk.noteapp.service;

import com.vmo.hungnk.noteapp.entity.Note;
import org.springframework.web.multipart.MultipartFile;

public interface NoteImageService {

    void saveImage(Long userID, Note note, MultipartFile image);

    boolean deleteImage(Long userID, Long noteID, Long noteImageID);
}
