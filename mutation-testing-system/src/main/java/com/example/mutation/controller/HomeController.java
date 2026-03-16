package com.example.mutation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("title", "主界面");
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

