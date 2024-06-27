package com.example.demo.serivce.impl;

import com.example.demo.entity.University;
import com.example.demo.serivce.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    private final RestTemplate rt;
    private final ExecutorService pool;

    @Value("${university-url}")
    private String url;

    @Autowired
    public UniversityServiceImpl(RestTemplate rt, ExecutorService pool){
        this.rt = rt;
        this.pool = pool;
    }

    @Override
    public University[] searchUniversities() {
        return rt.getForObject(url, University[].class);
    }

    @Override
    public List<University> searchUniversitiesByCountries(List<String> countries) {
        List<CompletableFuture<University[]>> futures = new ArrayList<>();
        countries.forEach(country -> {
            CompletableFuture<University[]> future = CompletableFuture.supplyAsync(() -> rt.getForObject(url + "?country=" + country, University[].class), pool);
            futures.add(future);
        });

        List<University> results = new ArrayList<>();
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                .thenAccept(v -> futures.forEach(future -> Optional.ofNullable(future.join()).ifPresent(universities -> results.addAll(Arrays.asList(universities)))))
                .join();


        return results;
    }

}
