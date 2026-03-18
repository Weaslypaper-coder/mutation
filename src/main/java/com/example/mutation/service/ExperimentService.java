package com.example.mutation.service;

import com.example.mutation.entity.MutantBranch;
import com.example.mutation.entity.Program;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExperimentService {

    private final List<MutantBranch> mutantBranches = new ArrayList<>();
    private final List<String> generatedPaths = new ArrayList<>();
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
        generatedPaths.clear();
    }

    public void generateMutants(int count) {
        int existing = mutantBranches.size();
        for (int i = 1; i <= count; i++) {
            long id = existing + i;
            mutantBranches.add(new MutantBranch(id, "Mutant_" + id));
        }
    }

    public void clearPaths() {
        generatedPaths.clear();
    }

    /**
     * 演示用：基于已有变异分支生成“路径集合”。
     * 在真实系统中，这里会基于相关度矩阵与相关图做路径选择/覆盖优化。
     */
    public void generatePaths(int count) {
        generatedPaths.clear();
        int n = Math.min(count, mutantBranches.size() > 0 ? mutantBranches.size() : count);
        for (int i = 1; i <= n; i++) {
            generatedPaths.add("Path_" + i + " (from Mutant_" + i + ")");
        }
        // 若没有分支也能演示生成
        for (int i = n + 1; i <= count; i++) {
            generatedPaths.add("Path_" + i);
        }
    }

    public List<String> getGeneratedPaths() {
        return new ArrayList<>(generatedPaths);
    }

    public int countPaths() {
        return generatedPaths.size();
    }

    public int countMutants() {
        return mutantBranches.size();
    }
}

