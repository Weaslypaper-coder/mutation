package com.example.mutation.entity;

public class ExperimentParameter {

    private Integer populationSize;
    private String encodingType;
    private Double crossoverRate;
    private Double mutationRate;
    private Integer sampleSize;

    public ExperimentParameter() {
    }

    public ExperimentParameter(Integer populationSize, String encodingType, Double crossoverRate,
                               Double mutationRate, Integer sampleSize) {
        this.populationSize = populationSize;
        this.encodingType = encodingType;
        this.crossoverRate = crossoverRate;
        this.mutationRate = mutationRate;
        this.sampleSize = sampleSize;
    }

    public Integer getPopulationSize() {
        return populationSize;
    }

    public void setPopulationSize(Integer populationSize) {
        this.populationSize = populationSize;
    }

    public String getEncodingType() {
        return encodingType;
    }

    public void setEncodingType(String encodingType) {
        this.encodingType = encodingType;
    }

    public Double getCrossoverRate() {
        return crossoverRate;
    }

    public void setCrossoverRate(Double crossoverRate) {
        this.crossoverRate = crossoverRate;
    }

    public Double getMutationRate() {
        return mutationRate;
    }

    public void setMutationRate(Double mutationRate) {
        this.mutationRate = mutationRate;
    }

    public Integer getSampleSize() {
        return sampleSize;
    }

    public void setSampleSize(Integer sampleSize) {
        this.sampleSize = sampleSize;
    }
}

