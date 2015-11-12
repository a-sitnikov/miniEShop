package com.acsent.controller;

import com.acsent.model.AppUser;
import com.acsent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login1(Model model, @RequestParam("username") String username){

        System.out.println(username);
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login1(Model model){

        return "index1";
    }

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
