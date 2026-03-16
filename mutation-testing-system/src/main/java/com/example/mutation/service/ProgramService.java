package com.example.mutation.service;

import com.example.mutation.entity.Program;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProgramService {

    private final List<Program> programs = new ArrayList<>();

    public List<Program> findAll() {
        return new ArrayList<>(programs);
    }

    public Optional<Program> findById(Long id) {
        return programs.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public Program create(String programName, String language) {
        long nextId = programs.stream().mapToLong(p -> p.getId() == null ? 0L : p.getId()).max().orElse(0L) + 1;
        Program program = new Program(nextId, programName, language);
        programs.add(program);
        return program;
    }

    public void delete(Long id) {
        programs.removeIf(p -> p.getId().equals(id));
    }
}

