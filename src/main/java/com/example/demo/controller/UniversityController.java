package com.example.demo.controller;

import com.example.demo.entity.University;
import com.example.demo.serivce.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/universities")
public class UniversityController {
    @Autowired
    private UniversityService universityService;

    @GetMapping
    public ResponseEntity<List<University>> searchUniversities() {
        List<University> responses = universityService.searchUniversities();
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<List<University>> searchUniversitiesByCountries(@RequestBody List<String> countries) {
        List<University> responses = universityService.searchUniversitiesByCountries(countries);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
}
