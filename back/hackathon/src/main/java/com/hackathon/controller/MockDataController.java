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

        List<String> resultsForFinalTest = mockDataService.searchResultsByEmail(email, "Springboot");
        List<String> resultSqlReTest= mockDataService.searchResultsByEmail(email, "Linux");
        List<String> resultTotal= mockDataService.searchResultsByEmail(email, "Total");
        List<String> resultRound1= mockDataService.searchResultsByEmail(email, "R1score");


        Map<String, List<String>> combinedResults = new HashMap<>();
        combinedResults.put("Linux", resultSqlReTest);
        combinedResults.put("SpringBoot", resultsForFinalTest);
        combinedResults.put("Total", resultTotal);
        combinedResults.put("R1score", resultRound1);


        System.out.println("Result: " + combinedResults);
        if (resultSqlReTest.isEmpty() && resultsForFinalTest.isEmpty() && resultTotal.isEmpty() && resultRound1.isEmpty() ) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(combinedResults);
        }

    }
}
