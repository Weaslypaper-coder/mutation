package com.example.mutation.entity;

public class MutantBranch {

    private Long id;
    private String branchName;

    public MutantBranch() {
    }

    public MutantBranch(Long id, String branchName) {
        this.id = id;
        this.branchName = branchName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }
}

