package com.endl.loopin.controller;

import com.endl.loopin.service.HealthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/health")
public class HealthController {

    @Autowired
    private HealthService healthService;

    @GetMapping
    public String getHealthStatus() {
        return healthService.checkHealth();
    }
}
