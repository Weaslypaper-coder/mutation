package com.example.mutation.service;

import com.example.mutation.entity.ModelResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ModelService {

    private final List<ModelResult> results = new ArrayList<>();

    public List<ModelResult> getResults() {
        return new ArrayList<>(results);
    }

    public void clear() {
        results.clear();
    }

    public void addResult(ModelResult result) {
        results.add(result);
    }
}

