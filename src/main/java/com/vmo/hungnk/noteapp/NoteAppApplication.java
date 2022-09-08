package com.vmo.hungnk.noteapp;

import com.vmo.hungnk.noteapp.config.FileStorageConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(FileStorageConfig.class)
public class NoteAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoteAppApplication.class, args);
    }

    //  TODO: Create Note Controller
    //  TODO: Create Text Note HTML
    //  TODO: Create Image Note HTML
    //  TODO: UT for User Service
    //  TODO: UT for User Repo
    //  TODO: UT for Note Repo
    //  TODO: UT for Note Service

}
