package com.example.mutation.controller;

import com.example.mutation.entity.Program;
import com.example.mutation.service.ExperimentService;
import com.example.mutation.service.ProgramService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/experiment")
public class ExperimentController {

    private final ExperimentService experimentService;
    private final ProgramService programService;

    public ExperimentController(ExperimentService experimentService, ProgramService programService) {
        this.experimentService = experimentService;
        this.programService = programService;
    }

    @PostMapping("/start")
    public String startExperiment(@RequestParam("system") String system,
                                  @RequestParam("project") String project,
                                  @RequestParam("language") String language,
                                  Model model) {
        String programName = system + "-" + project;
        Program program = programService.create(programName, language);
        experimentService.setCurrentProgram(program);
        return "redirect:/experiment/program-test";
    }

    @GetMapping("/program-test")
    public String programTest(Model model) {
        model.addAttribute("program", experimentService.getCurrentProgram());
        model.addAttribute("mutantCount", experimentService.countMutants());
        model.addAttribute("mutants", experimentService.getMutantBranches());
        return "program-test";
    }

    @PostMapping("/import-program")
    public String importProgram(@RequestParam("programName") String programName) {
        Program program = programService.create(programName, "Java");
        experimentService.setCurrentProgram(program);
        return "redirect:/experiment/program-test";
    }

    @PostMapping("/generate-mutants")
    public String generateMutants() {
        experimentService.clearMutantBranches();
        experimentService.generateMutants(20);
        return "redirect:/experiment/program-test";
    }

    @GetMapping("/correlation-heatmap")
    public String correlationHeatmap() {
        return "correlation-heatmap";
    }

    @GetMapping("/correlation-graph")
    public String correlationGraph() {
        return "correlation-graph";
    }

    @GetMapping("/data-generation")
    public String dataGeneration() {
        return "data-generation";
    }
}

