package com.vmo.hungnk.noteapp.entity;

import com.vmo.hungnk.noteapp.constant.NoteType;
import com.vmo.hungnk.noteapp.constant.converter.NoteTypeConverter;
import java.util.Set;
import javax.persistence.*;

import lombok.*;

@Table(name = "NOTE")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Note extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "NOTE_ID", unique = true, nullable = false)
    private Long noteID;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "TEXT_DESCRIPTION")
    private String textDescription;

    @Convert(converter = NoteTypeConverter.class)
    @Column(name = "NOTE_TYPE")
    private NoteType noteType = NoteType.TEXT;

    @Column(name = "COMPLETED")
    private boolean completed = false;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private NoteUser noteUser;

    @OneToOne(mappedBy = "note")
    private NoteImage image;


    @OneToMany(mappedBy = "note", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<NoteCheckbox> checkBoxes;
}
