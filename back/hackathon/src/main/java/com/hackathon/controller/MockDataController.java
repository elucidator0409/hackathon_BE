package com.hackathon.controller;

import com.hackathon.service.MockDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/mockdata")
public class MockDataController {

    private final MockDataService mockDataService;

    @Autowired
    public MockDataController(MockDataService mockDataService) {
        this.mockDataService = mockDataService;
    }

    @GetMapping("/results/{email}")
    public ResponseEntity<List<String>> getResultsByEmail(@PathVariable String email) {
        List<String> results = mockDataService.searchResultsByEmail(email);
        System.out.println("Result: " + results);
        if (results.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(results);
        }
    }
}
