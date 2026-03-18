package com.example.mutation.config;

import com.example.mutation.entity.ExperimentParameter;
import com.example.mutation.entity.Program;
import com.example.mutation.service.ExperimentService;
import com.example.mutation.service.ModelService;
import com.example.mutation.service.ParameterService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class GlobalViewModelAdvice {

    private final ExperimentService experimentService;
    private final ParameterService parameterService;
    private final ModelService modelService;

    public GlobalViewModelAdvice(ExperimentService experimentService,
                                 ParameterService parameterService,
                                 ModelService modelService) {
        this.experimentService = experimentService;
        this.parameterService = parameterService;
        this.modelService = modelService;
    }

    @ModelAttribute("currentPath")
    public String currentPath(HttpServletRequest request) {
        return request.getRequestURI();
    }

    @ModelAttribute("expCurrentStep")
    public int expCurrentStep(@ModelAttribute("currentPath") String currentPath) {
        if (currentPath == null) return 0;
        if (currentPath.startsWith("/experiment/program-test")) return 1;
        if (currentPath.startsWith("/parameter/setting")) return 3;
        if (currentPath.startsWith("/experiment/path-generation")) return 4;
        if (currentPath.startsWith("/experiment/correlation-heatmap")) return 4; // 兼容旧路径
        if (currentPath.startsWith("/experiment/correlation-graph")) return 4;   // 兼容旧路径
        if (currentPath.startsWith("/model/training")) return 5;
        if (currentPath.startsWith("/experiment/data-generation")) return 6;
        if (currentPath.startsWith("/experiment")) return 1;
        return 0;
    }

    @ModelAttribute("expProgramName")
    public String expProgramName() {
        Program p = experimentService.getCurrentProgram();
        return p != null ? p.getProgramName() : null;
    }

    @ModelAttribute("expHasProgram")
    public boolean expHasProgram() {
        return experimentService.getCurrentProgram() != null;
    }

    @ModelAttribute("expMutantCount")
    public int expMutantCount() {
        return experimentService.countMutants();
    }

    @ModelAttribute("expHasMutants")
    public boolean expHasMutants() {
        return experimentService.countMutants() > 0;
    }

    @ModelAttribute("expPathCount")
    public int expPathCount() {
        return experimentService.countPaths();
    }

    @ModelAttribute("expHasPaths")
    public boolean expHasPaths() {
        return experimentService.countPaths() > 0;
    }

    @ModelAttribute("expHasParameter")
    public boolean expHasParameter() {
        ExperimentParameter p = parameterService.getCurrentParameter();
        return p != null;
    }

    @ModelAttribute("expHasModelResult")
    public boolean expHasModelResult() {
        return !modelService.getResults().isEmpty();
    }

    @ModelAttribute("expAllDone")
    public boolean expAllDone(@ModelAttribute("expHasProgram") boolean expHasProgram,
                              @ModelAttribute("expHasMutants") boolean expHasMutants,
                              @ModelAttribute("expHasParameter") boolean expHasParameter,
                              @ModelAttribute("expHasPaths") boolean expHasPaths,
                              @ModelAttribute("expHasModelResult") boolean expHasModelResult) {
        return expHasProgram && expHasMutants && expHasParameter && expHasPaths && expHasModelResult;
    }
}

