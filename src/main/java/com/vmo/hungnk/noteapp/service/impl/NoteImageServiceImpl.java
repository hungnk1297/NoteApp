package com.vmo.hungnk.noteapp.service.impl;

import com.vmo.hungnk.noteapp.entity.Note;
import com.vmo.hungnk.noteapp.entity.NoteImage;
import com.vmo.hungnk.noteapp.repository.NoteImageRepository;
import com.vmo.hungnk.noteapp.service.NoteImageService;
import com.vmo.hungnk.noteapp.util.ImageUtil;
import com.vmo.hungnk.noteapp.util.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

@Service
@RequiredArgsConstructor
@Slf4j
public class NoteImageServiceImpl implements NoteImageService {

    //    Repositories
    private final NoteImageRepository noteImageRepo;

    //    Utils
    private final ImageUtil imageUtil;
    private final Validator validator;
    private final Path uploadImagePath;

    @Override
    public void saveImage(Long userID, Note note, MultipartFile image) {
        Path uploadedImagePath = imageUtil.uploadImage(userID, image);
        NoteImage noteImage = NoteImage.builder()
                .note(note)
                .url(uploadImagePath.relativize(uploadedImagePath).toString())
                .build();

        note.setImage(noteImage);
    }

    @Override
    public boolean deleteImage(Long userID, Long noteID, Long noteImageID) {
        NoteImage deletingImage = validator.validateNoteImage(userID, noteID, noteImageID);
        Note note = deletingImage.getNote();

        //  Delete in directory
        boolean deleteDirResult = imageUtil.deleteImageByURL(deletingImage.getUrl());

        if(deleteDirResult){
        //  Delete in DB
            note.setImage(null);
            noteImageRepo.delete(deletingImage);
        }
        return true;
    }
}
