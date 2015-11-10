package com.acsent.controller;

import com.acsent.model.RoleEnum;
import com.acsent.model.User;
import com.acsent.model.UserRole;
import com.acsent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;

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

        User user = new User();
        model.addAttribute("user", user);

        return "/login/create";
    }
    @RequestMapping(value = "/login/create", method = RequestMethod.POST)
    public String createPost(@ModelAttribute(value="user") User user, Model model){

        user.getRoles().add(new UserRole(
                RoleEnum.USER.toString(),
                user
        ));
        userRepository.save(user);

        return "/login/home";
    }
}
