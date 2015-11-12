package com.acsent.controller;

import com.acsent.model.AppUser;
import com.acsent.repository.UserRepository;
import com.acsent.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.inject.Inject;

@Controller
public class FacebookController {

    //https://spring.io/guides/gs/accessing-facebook/
    private Facebook facebook;

    @Autowired
    private UserRepository userRepository;

    @Inject
    public FacebookController(Facebook facebook) {
        this.facebook = facebook;
    }

    //@RequestMapping(name = "/connect/facebookConnected", method = RequestMethod.GET)
    public String facebookConnected(Model model) {
        if (!facebook.isAuthorized()) {
            return "1";//"redirect:/connect/facebook";
        }

        AppUser appUser = SecurityUtils.getAppUser();
        if (appUser == null) {
            appUser = new AppUser();
        } else {

        }
        model.addAttribute("facebookProfile", facebook.userOperations().getUserProfile());

        return "redirect:/";
    }

}
