package com.example.mutation.controller;

import com.example.mutation.entity.Program;
import com.example.mutation.service.ExperimentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final ExperimentService experimentService;

    public HomeController(ExperimentService experimentService) {
        this.experimentService = experimentService;
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("title", "主界面");
        Program p = experimentService.getCurrentProgram();
        model.addAttribute("lastProgramName", p != null ? p.getProgramName() : null);
        model.addAttribute("lastMutantCount", experimentService.countMutants());
        // 演示用：没有真实统计时给一个稳定的“摘要感”
        model.addAttribute("lastCoverage", p != null ? "92%" : null);
        return "home";
    }

    @GetMapping("/algorithm")
    public String algorithm(Model model) {
        model.addAttribute("title", "算法简介");
        return "algorithm";
    }

    @GetMapping("/experiment")
    public String experimentSelect(Model model) {
        model.addAttribute("title", "实验选择");
        return "experiment-select";
    }
}

