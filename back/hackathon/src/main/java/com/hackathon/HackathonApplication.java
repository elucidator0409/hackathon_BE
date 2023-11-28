package com.hackathon;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackathon.entity.MockData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class HackathonApplication implements CommandLineRunner {

    public static void main(String[] args) {

        SpringApplication.run(HackathonApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        readData();
    }

    private void readData() {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Replace "path/to/mockdata.json" with the actual path to your JSON file
            File file = new File("hackathon/mockdata.json");

            // Read JSON file and convert to a list of MockData objects
            List<MockData> mockDataList = Arrays.asList(objectMapper.readValue(file, MockData[].class));

            // Print the data for verification
            mockDataList.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
