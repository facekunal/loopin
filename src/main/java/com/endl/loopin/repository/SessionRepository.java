package com.endl.loopin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.endl.loopin.entity.Session;
import com.endl.loopin.util.session.SessionStatus;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, UUID> {

    Optional<Session> findByUserId(String userId);

    List<Session> findByUserIdAndStatus(String userId, SessionStatus status);

    void deleteByUserId(String userId);
}
