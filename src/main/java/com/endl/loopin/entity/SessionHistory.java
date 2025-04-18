package com.endl.loopin.entity;

import lombok.Data;

import java.time.Instant;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "sessions_history")
@Data // generate getters, setters, toString, equals, and hashCode
public class SessionHistory {
    @Id
    private UUID id;

    private String userId;

    private Instant startedAt;

    private Instant lastActiveAt;
    
    private String userAgent;

    @CreatedDate
    @Column(updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;
}
