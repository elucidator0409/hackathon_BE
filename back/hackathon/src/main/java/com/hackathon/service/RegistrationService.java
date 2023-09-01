package com.hackathon.service;

import com.hackathon.entity.News;
import com.hackathon.entity.Registration;
import com.hackathon.repository.RegistrationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Transactional
@Service
public class RegistrationService {

    @Autowired
    private RegistrationRepository registrationRepository;

    public List<Registration> getAllRegistrations() {
        log.debug("getAllRegistrations: ");
        return registrationRepository.findAll();
    }

    public Registration addRegistration(Registration registration) {
        registration.onActive();
        return registrationRepository.save(registration);
    }
}