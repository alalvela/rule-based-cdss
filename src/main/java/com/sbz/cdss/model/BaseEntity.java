package com.sbz.cdss.model;

import lombok.Data;

import javax.persistence.*;

@MappedSuperclass
@Data
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted;

    @PrePersist
    private void prePersist() {
        this.setDeleted(false);
    }
}
