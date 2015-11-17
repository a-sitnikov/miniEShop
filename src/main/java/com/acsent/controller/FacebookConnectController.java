package com.acsent.controller;

import com.acsent.config.FacebookConnectInterceptor;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.connect.web.ConnectController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Controller
@RequestMapping({"/connect"})
public class FacebookConnectController extends ConnectController {

    //http://stackoverflow.com/questions/19915819/override-spring-social-connectcontroller-and-add-interceptor
    FacebookConnectInterceptor facebookConnectInterceptor;

    @Inject
    public FacebookConnectController(
            ConnectionFactoryLocator connectionFactoryLocator,
            ConnectionRepository connectionRepository,
            FacebookConnectInterceptor facebookConnectInterceptor) {

        super(connectionFactoryLocator, connectionRepository);
        this.facebookConnectInterceptor = facebookConnectInterceptor;
    }

    @PostConstruct
    public void addInterceptor(){
        this.addInterceptor(facebookConnectInterceptor);
    }

    @Override
    protected String connectedView(String providerId){
        return "redirect:/";
    }

}
