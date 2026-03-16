package com.example.mutation.controller;

import com.example.mutation.entity.ExperimentParameter;
import com.example.mutation.service.ParameterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/parameter")
public class ParameterController {

    private final ParameterService parameterService;

    public ParameterController(ParameterService parameterService) {
        this.parameterService = parameterService;
    }

    @GetMapping("/setting")
    public String parameterSetting(Model model) {
        model.addAttribute("parameter", parameterService.getCurrentParameter());
        return "parameter-setting";
    }

    @PostMapping("/save")
    public String saveParameter(@RequestParam("populationSize") Integer populationSize,
                                @RequestParam("encodingType") String encodingType,
                                @RequestParam("crossoverRate") Double crossoverRate,
                                @RequestParam("mutationRate") Double mutationRate,
                                @RequestParam("sampleSize") Integer sampleSize) {
        ExperimentParameter parameter = new ExperimentParameter(
                populationSize,
                encodingType,
                crossoverRate,
                mutationRate,
                sampleSize
        );
        parameterService.saveParameter(parameter);
        return "redirect:/experiment/program-test";
    }
}

