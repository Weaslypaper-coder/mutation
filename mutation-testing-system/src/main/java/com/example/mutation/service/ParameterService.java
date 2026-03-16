package com.example.mutation.service;

import com.example.mutation.entity.ExperimentParameter;
import org.springframework.stereotype.Service;

@Service
public class ParameterService {

    private ExperimentParameter currentParameter;

    public ExperimentParameter getCurrentParameter() {
        return currentParameter;
    }

    public void saveParameter(ExperimentParameter parameter) {
        this.currentParameter = parameter;
    }
}

