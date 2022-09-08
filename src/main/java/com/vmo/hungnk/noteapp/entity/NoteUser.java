package com.vmo.hungnk.noteapp.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Table(name = "USERS")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteUser extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID", unique = true, nullable = false)
    private Long userID;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "ENCRYPTED_PASSWORD")
    private String encryptedPassword;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, mappedBy = "noteUser")
    private List<Note> notes;
}
