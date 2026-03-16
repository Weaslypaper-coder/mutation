package com.example.mutation.service;

import com.example.mutation.entity.MutantBranch;
import com.example.mutation.entity.Program;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExperimentService {

    private final List<MutantBranch> mutantBranches = new ArrayList<>();
    private Program currentProgram;

    public Program getCurrentProgram() {
        return currentProgram;
    }

    public void setCurrentProgram(Program currentProgram) {
        this.currentProgram = currentProgram;
    }

    public List<MutantBranch> getMutantBranches() {
        return new ArrayList<>(mutantBranches);
    }

    public void clearMutantBranches() {
        mutantBranches.clear();
    }

    public void generateMutants(int count) {
        int existing = mutantBranches.size();
        for (int i = 1; i <= count; i++) {
            long id = existing + i;
            mutantBranches.add(new MutantBranch(id, "Mutant_" + id));
        }
    }

    public int countMutants() {
        return mutantBranches.size();
    }
}

