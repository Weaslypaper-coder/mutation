package com.example.mutation.controller;

import com.example.mutation.entity.Program;
import com.example.mutation.service.ExperimentService;
import com.example.mutation.service.ModelService;
import com.example.mutation.service.ParameterService;
import com.example.mutation.service.ProgramService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/experiment")
public class ExperimentController {

    private final ExperimentService experimentService;
    private final ProgramService programService;
    private final ParameterService parameterService;
    private final ModelService modelService;

    public ExperimentController(ExperimentService experimentService,
                                ProgramService programService,
                                ParameterService parameterService,
                                ModelService modelService) {
        this.experimentService = experimentService;
        this.programService = programService;
        this.parameterService = parameterService;
        this.modelService = modelService;
    }

    @PostMapping("/start")
    public String startExperiment(@RequestParam("system") String system,
                                  @RequestParam("project") String project,
                                  @RequestParam("language") String language,
                                  Model model,
                                  RedirectAttributes redirectAttributes) {
        String programName = system + "-" + project;
        Program program = programService.create(programName, language);
        experimentService.setCurrentProgram(program);
        redirectAttributes.addAttribute("msg", "已创建实验：" + programName + "（" + language + "）");
        return "redirect:/experiment/program-test"; //

    }

    @GetMapping("/program-test")
    public String programTest(Model model) {
        model.addAttribute("program", experimentService.getCurrentProgram());
        model.addAttribute("mutantCount", experimentService.countMutants());
        model.addAttribute("mutants", experimentService.getMutantBranches());
        return "program-test";
    }

    @PostMapping("/import-program")
    public String importProgram(@RequestParam("programName") String programName,
                                RedirectAttributes redirectAttributes) {
        Program program = programService.create(programName, "Java");
        experimentService.setCurrentProgram(program);
        redirectAttributes.addAttribute("msg", "程序导入成功：" + programName);
        return "redirect:/experiment/program-test";
    }

    @PostMapping("/generate-mutants")
    public String generateMutants(RedirectAttributes redirectAttributes) {
        experimentService.clearMutantBranches();
        experimentService.generateMutants(20);
        redirectAttributes.addAttribute("msg", "已生成 20 个变异分支（Mutant_1 ~ Mutant_20）");
        return "redirect:/experiment/program-test";
        //
    }

    @GetMapping("/correlation-heatmap")
    public String correlationHeatmap() {
        return "redirect:/experiment/path-generation";
    }

    @GetMapping("/correlation-graph")
    public String correlationGraph() {
        return "redirect:/experiment/path-generation";
    }

    @GetMapping("/path-generation")
    public String pathGeneration(Model model) {
        model.addAttribute("paths", experimentService.getGeneratedPaths());
        return "path-generation";
    }

    @PostMapping("/generate-paths")
    public String generatePaths(RedirectAttributes redirectAttributes) {
        experimentService.generatePaths(20);
        redirectAttributes.addAttribute("msg", "已基于相关度与相关图生成 20 条路径（示例）");
        return "redirect:/experiment/path-generation";
    }

    @GetMapping("/data-generation")
    public String dataGeneration() {
        return "data-generation";
    }

    @GetMapping("/report")
    public String report(Model model) {
        model.addAttribute("program", experimentService.getCurrentProgram());
        model.addAttribute("mutantCount", experimentService.countMutants());
        model.addAttribute("mutants", experimentService.getMutantBranches());
        model.addAttribute("parameter", parameterService.getCurrentParameter());
        model.addAttribute("results", modelService.getResults());
        return "experiment-report";
    }
}

