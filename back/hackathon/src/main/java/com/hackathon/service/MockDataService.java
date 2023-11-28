package com.hackathon.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackathon.entity.MockData;
import com.hackathon.repository.MockDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class MockDataService {

    private final MockDataRepository mockDataRepository;

    @Autowired
    public MockDataService(MockDataRepository mockDataRepository) {
        this.mockDataRepository = mockDataRepository;
    }
    public List<String> searchResultsByEmail(String email) {
        return mockDataRepository.findResultsByEmail(email);
    }

    @PostConstruct
    public void init() {
        readAndSaveMockDataFromFile();
    }

    public void readAndSaveMockDataFromFile() {
        mockDataRepository.deleteAllMockData();
        mockDataRepository.resetMockDataId();

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Replace "path/to/mockdata.json" with the actual path to your JSON file
            File file = new File("hackathon/mockdata.json");

            // Read JSON file and convert to a list of MockData objects
            List<MockData> mockDataList = Arrays.asList(objectMapper.readValue(file, MockData[].class));

            // Save the list of MockData objects to the database
            mockDataRepository.saveAll(mockDataList);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
