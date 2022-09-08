package com.vmo.hungnk.noteapp.repository;

import com.vmo.hungnk.noteapp.entity.NoteImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteImageRepository extends JpaRepository<NoteImage, Long> {

}