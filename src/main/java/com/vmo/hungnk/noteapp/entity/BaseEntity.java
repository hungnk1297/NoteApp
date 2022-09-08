package com.vmo.hungnk.noteapp.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {

    @Column(name = "DELETED")
    protected boolean deleted = false;

    @Column(name = "CREATED_ON")
    protected LocalDateTime createdOn = LocalDateTime.now();

    @Column(name = "LAST_MODIFIED")
    protected LocalDateTime lastModified;
}
