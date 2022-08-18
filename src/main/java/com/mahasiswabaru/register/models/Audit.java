package com.mahasiswabaru.register.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;

import java.time.LocalDateTime;

import static javax.persistence.TemporalType.TIMESTAMP;

@Data
@EqualsAndHashCode
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Audit<T> {
    @Column(name = "created_at", updatable = false)
//    @Temporal(TIMESTAMP)
    @CreatedDate
    protected LocalDateTime createdAt;

    @Column(name = "updated_at")
//    @Temporal(TIMESTAMP)
    @LastModifiedDate
    protected LocalDateTime updatedAt;
}
