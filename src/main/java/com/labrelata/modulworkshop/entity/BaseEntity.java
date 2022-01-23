package com.labrelata.modulworkshop.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@SuperBuilder
@MappedSuperclass
@NoArgsConstructor
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = -1436072899548110603L;

    @Column(name = "created_at", nullable = false)
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    @PrePersist
    @SuppressWarnings("unused")
    void onCreate() {
        this.createdAt = Timestamp.valueOf(LocalDateTime.now());
    }

    @PreUpdate
    @SuppressWarnings("unused")
    void onUpdate() {
        this.updatedAt = Timestamp.valueOf(LocalDateTime.now());
    }

}
