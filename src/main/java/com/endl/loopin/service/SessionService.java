package com.endl.loopin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.endl.loopin.repository.SessionRepository;
import com.endl.loopin.entity.Session;
import com.endl.loopin.util.session.SessionStatus;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SessionService {
    
    @Autowired
    private SessionRepository sessionRepository;

    public Session createSession(String userId, String userAgent) {
        Session session = new Session();
        session.setUserId(userId);
        session.setUserAgent(userAgent);
        session.setStartedAt(Instant.now());
        session.setLastActiveAt(Instant.now());
        session.setStatus(SessionStatus.ACTIVE);
        return sessionRepository.save(session);
    }

    public Optional<Session> getSession(UUID sessionId) {
        return sessionRepository.findById(sessionId);
    }

    public void refreshSessionActivity(UUID sessionId) {
        sessionRepository.findById(sessionId).ifPresent(session -> {
            if (session.getStatus() == SessionStatus.ACTIVE) {
                session.setLastActiveAt(Instant.now());
                sessionRepository.save(session);
            }
        });
    }

    public void invalidateSession(UUID sessionId) {
        sessionRepository.findById(sessionId).ifPresent(session -> {
            if (session.getStatus() != SessionStatus.LOGGED_OUT) {
                session.setStatus(SessionStatus.LOGGED_OUT);
                sessionRepository.save(session);
            }
        });
    }

    @Transactional
    public void invalidateAllSessionsForUser(String userId) {
        Optional<Session> sessions = sessionRepository.findByUserId(userId); // Reverted type to Optional<Session>
        sessions.ifPresent(session -> {
            if (session.getStatus() != SessionStatus.LOGGED_OUT) {
                session.setStatus(SessionStatus.LOGGED_OUT);
            }
            sessionRepository.save(session);
        });
    }

    public List<Session> listActiveSessions(String userId) {
        return sessionRepository.findByUserIdAndStatus(userId, SessionStatus.ACTIVE);
    }

    public boolean isSessionValid(UUID sessionId) {
        return sessionRepository.findById(sessionId)
                .filter(session -> session.getStatus() == SessionStatus.ACTIVE)
                .isPresent();
    }
}
