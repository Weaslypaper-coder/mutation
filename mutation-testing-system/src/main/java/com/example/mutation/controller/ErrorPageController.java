package com.example.mutation.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorPageController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute("jakarta.servlet.error.status_code");
        if (status != null) {
            try {
                int code = Integer.parseInt(status.toString());
                if (code == 404) {
                    // 所有 404 统一跳转到登录页，避免 Whitelabel 页面
                    return "redirect:/login";
                }
            } catch (NumberFormatException ignored) {
            }
        }
        // 其他错误也统一回登录页即可
        return "redirect:/login";
    }
}

