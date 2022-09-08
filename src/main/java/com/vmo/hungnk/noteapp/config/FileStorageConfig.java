package com.vmo.hungnk.noteapp.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@ConfigurationProperties(prefix = "file")
@Component
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class FileStorageConfig {

    @Value("${file.upload-image}")
    private String uploadImageDirectory;

    @Bean
    Path uploadImagePath() {
        var uploadPath = Path.of(uploadImageDirectory);
        try {
            Files.createDirectories(uploadPath);
        } catch (IOException ioException) {
            log.error("Could not create the upload path directory!");
        }
        return Path.of(uploadImageDirectory);
    }
}
