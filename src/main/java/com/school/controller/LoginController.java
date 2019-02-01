package com.school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/login")
@Controller
public class LoginController {

    @GetMapping("/index")
    public String index(){
        return "/home";
    }


    @GetMapping("/login/error")
    public String error(Model model){
        model.addAttribute("msg", "登录失败");
        model.addAttribute("url", "/login");
        return "/home";
    }

    @ResponseBody
    @RequestMapping("/user/index")
    public String userindex(){
        return "index";
    }





}
