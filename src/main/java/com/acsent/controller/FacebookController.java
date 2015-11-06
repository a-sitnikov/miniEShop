package com.acsent.controller;

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

    @Inject
    public FacebookController(Facebook facebook) {
        this.facebook = facebook;
    }

    @RequestMapping(value = "/facebook", method= RequestMethod.GET)
    public String helloFacebook(Model model) {
/*        if (!facebook.isAuthorized()) {
            return "redirect:/connect/facebook";
        }

        model.addAttribute(facebook.userOperations().getUserProfile());
        PagedList<Post> homeFeed = facebook.feedOperations().getHomeFeed();
        model.addAttribute("feed", homeFeed);
*/
        return "facebook";
    }

    @RequestMapping(value = "/facebook", method = RequestMethod.POST)
    public String facebookPost() {
        return "facebook";
    }
}
