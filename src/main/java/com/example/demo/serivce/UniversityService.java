package com.example.demo.serivce;

import com.example.demo.entity.University;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public interface UniversityService {

    List<University> searchUniversities();
    List<University> searchUniversitiesByCountries(List<String> countries);
    List<University> processUniversities(University[] universities);
}
