package com.school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(){
        return "/login";
    }

    @PostMapping("/validate")
    public String validate(@RequestParam("userName") String username,
                           @RequestParam("password") String password,
                           Model model){
        model.addAttribute("msg", "登录失败");
        model.addAttribute("url", "/login");
        return null;
    }

    @GetMapping("/login/error")
    public String error(Model model){
        model.addAttribute("msg", "登录失败");
        model.addAttribute("url", "/login");
        return "/login";
    }



}
