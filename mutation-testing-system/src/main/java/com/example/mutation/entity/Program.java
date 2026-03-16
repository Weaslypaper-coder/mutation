package com.example.mutation.entity;

public class Program {

    private Long id;
    private String programName;
    private String language;

    public Program() {
    }

    public Program(Long id, String programName, String language) {
        this.id = id;
        this.programName = programName;
        this.language = language;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}

