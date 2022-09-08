package com.vmo.hungnk.noteapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.*;

@Table(name = "NOTE_CHECK_BOX")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteCheckbox extends BaseEntity {

    @Id
    @Column(name = "NOTE_CHECK_BOX_ID", nullable = false)
    private Long noteCheckBoxID;

    @ManyToOne
    @JoinColumn(name = "NOTE_ID", nullable = false)
    private Note note;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "CHECKED")
    private boolean checked;

}
