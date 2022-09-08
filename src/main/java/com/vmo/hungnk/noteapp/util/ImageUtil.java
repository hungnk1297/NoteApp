package com.vmo.hungnk.noteapp.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class ImageUtil {
    private final Path uploadImagePath;

    public Path uploadImage(Long userID, MultipartFile file) {
        Path imagePath = makeDirectoryByUserID(userID)
                .resolve(file.getOriginalFilename() != null ?
                        file.getOriginalFilename() : ("New Image - " + UUID.randomUUID()))
                .toAbsolutePath();
        try {
            Files.copy(file.getInputStream(), imagePath);
            return imagePath;
        } catch (IOException ioException) {
            log.error("Error when uploading image {}", file.getOriginalFilename());
            throw ExceptionUtil.errorCreatingDirectory("image", file.getOriginalFilename());
        }
    }

    public boolean deleteImageByURL(String urlFromDB) {
        Path deletingPath = uploadImagePath.resolve(Path.of(urlFromDB));
        try {
            if (Files.exists(deletingPath)) {
                return Files.deleteIfExists(deletingPath);
            }
        } catch (IOException ioException) {
            log.error("Error when deleting image with URL {}", urlFromDB);
            throw ExceptionUtil.errorDeletingImage("image", urlFromDB);
        }
        return false;
    }

    private Path makeDirectoryByUserID(Long userID) {
        Path userDirectory = uploadImagePath.resolve(userID.toString()).toAbsolutePath();
        try {
            if (!Files.exists(userDirectory)) {
                Files.createDirectories(userDirectory);
            }
        } catch (IOException ioException) {
            log.error("Error when creating directory for user with id {}", userID);
            throw ExceptionUtil.errorCreatingDirectory("userID", userID);
        }
        return userDirectory;
    }
}
