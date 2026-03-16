package com.example.mutation.entity;

public class ModelResult {

    private String modelName;
    private Double mseValue;
    private Double uStatistic;
    private Double iaStatistic;

    public ModelResult() {
    }

    public ModelResult(String modelName, Double mseValue, Double uStatistic, Double iaStatistic) {
        this.modelName = modelName;
        this.mseValue = mseValue;
        this.uStatistic = uStatistic;
        this.iaStatistic = iaStatistic;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Double getMseValue() {
        return mseValue;
    }

    public void setMseValue(Double mseValue) {
        this.mseValue = mseValue;
    }

    public Double getuStatistic() {
        return uStatistic;
    }

    public void setuStatistic(Double uStatistic) {
        this.uStatistic = uStatistic;
    }

    public Double getIaStatistic() {
        return iaStatistic;
    }

    public void setIaStatistic(Double iaStatistic) {
        this.iaStatistic = iaStatistic;
    }
}

