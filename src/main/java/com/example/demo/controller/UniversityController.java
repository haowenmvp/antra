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

    private final UniversityService universityService;

    @Autowired
    public UniversityController(UniversityService universityService) {
        this.universityService = universityService;
    }

    @GetMapping
    public ResponseEntity<?> searchUniversities() {
        return new ResponseEntity<>(universityService.searchUniversities(), HttpStatus.OK);
    }

    @GetMapping(params = "countries")
    public ResponseEntity<?> searchUniversitiesByCountries(@RequestParam("countries") List<String> countries) {
        List<University> responses = universityService.searchUniversitiesByCountries(countries);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
}
