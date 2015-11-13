package com.acsent.controller;

import com.acsent.model.AppUser;
import com.acsent.repository.UserRepository;
import com.acsent.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @Inject
    RememberMeServices rememberMeServices;

    @RequestMapping(value = "/login")
    public String login(Model model){

        return "redirect:/";
    }

    @RequestMapping("/login/login")
    public String login1(Model model, HttpServletRequest request, HttpServletResponse response){

        AppUser appUser = userRepository.findByUsername("1");
        SecurityUtils.logInUser(appUser, request, response, rememberMeServices);

        return "redirect:/";
    }

    @RequestMapping("/login/hello")
    public String hello(Model model){

        return "/login/hello";
    }

    @RequestMapping("/login/home")
    public String home(Model model){

        return "/login/home";
    }

    @RequestMapping(value = "/login/create", method = RequestMethod.GET)
    public String createGet(Model model){

        AppUser user = new AppUser();
        model.addAttribute("user", user);

        return "/login/create";
    }
    @RequestMapping(value = "/login/create", method = RequestMethod.POST)
    public String createPost(@ModelAttribute(value="user") AppUser user, Model model){

        userRepository.save(user);

        return "/login/home";
    }
}
