package com.acsent.config;

import com.acsent.model.AppUser;
import com.acsent.repository.UserRepository;
import com.acsent.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.web.ConnectInterceptor;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.UserOperations;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

public class FacebookConnectInterceptor implements ConnectInterceptor<Facebook> {

    @Autowired
    private UserRepository userRepository;

    @Inject
    RememberMeServices rememberMeServices;

    public void preConnect(ConnectionFactory<Facebook> provider, MultiValueMap<String, String> parameters, WebRequest request) {
        System.out.println("before");
    }

    public void postConnect(Connection<Facebook> connection, WebRequest request) {
        System.out.println("I've connected with the Spring Social Showcase!");

        String facebookId = connection.getKey().getProviderUserId();
        Facebook facebook = connection.getApi();

        String email = "";
        try {
            // trying to fetch wrong fields
            //email = connection.fetchUserProfile().getEmail();
            User facebookUser = facebook.fetchObject(facebookId, User.class, new String[]{"email"});
            email = facebookUser.getEmail();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        System.out.println(facebookId);
        System.out.println("email: " + email);

        AppUser appUser = userRepository.findByFacebookId(facebookId);
        if (appUser == null) {
            appUser = new AppUser();
            appUser.setFacebookId(facebookId);
            appUser.setUsername(facebookId);
            appUser.setEmail(email);
            appUser.setPassword("");
            appUser.setEnabled(true);
            userRepository.save(appUser);
        }

        HttpServletRequest httpServletRequest = ((ServletWebRequest)request).getRequest();
        SecurityUtils.logInUser(appUser, httpServletRequest, null, rememberMeServices);

    }

}
