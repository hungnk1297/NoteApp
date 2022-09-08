package com.vmo.hungnk.noteapp.entity;

import javax.persistence.*;

import lombok.*;

@Table(name = "NOTE_IMAGE")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteImage extends BaseEntity {

    @Id
    @Column(name = "NOTE_IMAGE_ID", nullable = false)
    private Long noteImageID;

    @OneToOne
    @JoinColumn(name = "NOTE_ID", nullable = false)
    private Note note;

    @Column(name = "URL")
    private String url;
}
