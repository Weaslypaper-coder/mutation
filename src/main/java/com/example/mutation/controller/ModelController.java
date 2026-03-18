package com.example.mutation.controller;

import com.example.mutation.entity.ModelResult;
import com.example.mutation.service.ModelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/model")
public class ModelController {

    private final ModelService modelService;

    public ModelController(ModelService modelService) {
        this.modelService = modelService;
    }

    @GetMapping("/training")
    public String trainingPage(Model model) {
        model.addAttribute("results", modelService.getResults());
        return "model-training";
    }

    @PostMapping("/train")
    public String train(@RequestParam("modelName") String modelName,
                        RedirectAttributes redirectAttributes) {
        modelService.clear();
        modelService.addResult(new ModelResult(modelName + " 模型", 0.015, 0.21, 0.89));
        redirectAttributes.addAttribute("msg", "训练完成：" + modelName + "（示例结果已生成）");
        return "redirect:/model/training";
    }
}

