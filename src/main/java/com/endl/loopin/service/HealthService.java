package com.endl.loopin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class HealthService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public String checkHealth() {
        try {
            // Execute a lightweight query to check DB connection
            jdbcTemplate.queryForObject("SELECT 1", Integer.class);
            return "Service is up and running";
        } catch (Exception e) {
            return "Service is up, but database connection failed: " + e.getMessage();
        }
    }
}
