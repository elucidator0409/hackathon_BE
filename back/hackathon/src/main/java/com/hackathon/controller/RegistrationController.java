package com.hackathon.controller;

import com.hackathon.entity.Registration;
import com.hackathon.repository.RegistrationRepository;
import com.hackathon.service.RegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/register")
public class RegistrationController {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private RegistrationService registrationService;

    @GetMapping
    public List<Registration> getAllRegistrations() {
        log.debug("getAllRegistrations: ");
        return registrationService.getAllRegistrations();
    }

    @PostMapping
    public ResponseEntity<Registration> registerUser(@RequestBody Registration registration) {
        log.debug("registerUser: ");
        try {
            Registration savedRegistration = registrationRepository.save(registration);
            return ResponseEntity.ok(savedRegistration);
        } catch (Exception e) {
            // Handle exceptions if needed
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}