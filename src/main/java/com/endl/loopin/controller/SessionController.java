package com.endl.loopin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import com.endl.loopin.service.SessionService;
import com.endl.loopin.entity.Session;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/sessions")
public class SessionController {
    
    @Autowired
    private SessionService sessionService;

    @PostMapping
    public Session createSession(@RequestParam String userId, @RequestParam String userAgent) {
        return sessionService.createSession(userId, userAgent);
    }

    @GetMapping("/{sessionId}")
    public Optional<Session> getSession(@PathVariable UUID sessionId) {
        return sessionService.getSession(sessionId);
    }

    @PutMapping("/{sessionId}/refresh")
    public void refreshSessionActivity(@PathVariable UUID sessionId) {
        sessionService.refreshSessionActivity(sessionId);
    }

    @PutMapping("/{sessionId}/invalidate")
    public void invalidateSession(@PathVariable UUID sessionId) {
        sessionService.invalidateSession(sessionId);
    }

    @PutMapping("/invalidate-all")
    public void invalidateAllSessionsForUser(@RequestParam String userId) {
        sessionService.invalidateAllSessionsForUser(userId);
    }

    @GetMapping("/active")
    public List<Session> listActiveSessions(@RequestParam String userId) {
        return sessionService.listActiveSessions(userId);
    }

    @GetMapping("/{sessionId}/valid")
    public boolean isSessionValid(@PathVariable UUID sessionId) {
        return sessionService.isSessionValid(sessionId);
    }
}
