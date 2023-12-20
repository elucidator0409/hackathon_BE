package com.hackathon.controller;

import com.hackathon.service.MockDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/mockdata")
public class MockDataController {

    private final MockDataService mockDataService;

    @Autowired
    public MockDataController(MockDataService mockDataService) {
        this.mockDataService = mockDataService;
    }

    @GetMapping("/results/{email}")
    public ResponseEntity<Map<String, List<String>>> getResultsByEmail(@PathVariable String email) {

        List<String> resultsForFinalTest = mockDataService.searchResultsByEmail(email, "SpringBoot");
        List<String> resultSqlReTest= mockDataService.searchResultsByEmail(email, "Sql");


        Map<String, List<String>> combinedResults = new HashMap<>();
        combinedResults.put("Sql", resultSqlReTest);
        combinedResults.put("SpringBoot", resultsForFinalTest);

        System.out.println("Result: " + combinedResults);
        if (resultSqlReTest.isEmpty() && resultsForFinalTest.isEmpty() ) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(combinedResults);
        }

    }
}
