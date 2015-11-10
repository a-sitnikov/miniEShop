package com.acsent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController {

    @RequestMapping("/login/login")
    public String login(Model model){

        return "/login/login";
    }

    @RequestMapping("/login/hello")
    public String hello(Model model){

        return "/login/hello";
    }

    @RequestMapping("/login/home")
    public String home(Model model){

        return "/login/home";
    }
}
