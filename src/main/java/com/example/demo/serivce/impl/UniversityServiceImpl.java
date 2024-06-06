package com.example.demo.serivce.impl;

import com.example.demo.entity.University;
import com.example.demo.serivce.UniversityService;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class UniversityServiceImpl implements UniversityService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String API_URL = "http://universities.hipolabs.com/search";

    public List<University> searchUniversities() {
        University[] universities = restTemplate.getForObject(API_URL, University[].class);
        return processUniversities(universities);
    }

    public List<University> searchUniversitiesByCountries(List<String> countries) {
        ExecutorService executor = Executors.newFixedThreadPool(countries.size());
        List<CompletableFuture<List<University>>> futures = new ArrayList<>();

        for (String country : countries) {
            CompletableFuture<List<University>> future = CompletableFuture.supplyAsync(() -> {
                University[] universities = restTemplate.getForObject(API_URL + "?country=" + country, University[].class);
                return processUniversities(universities);
            }, executor);
            futures.add(future);
        }

        List<University> results = new ArrayList<>();
        for (CompletableFuture<List<University>> future : futures) {
            try {
                results.addAll(future.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        executor.shutdown();
        return results;
    }

    public List<University> processUniversities(University[] universities) {
        List<University> responses = new ArrayList<>();
        if (universities != null) {
            for (University university : universities) {
                responses.add(new University(university.getName(), university.getWeb_pages(), university.getDomain()));
            }
        }
        return responses;
    }
}
