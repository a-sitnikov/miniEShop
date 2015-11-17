package com.acsent.config;

import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactory;
import org.springframework.social.connect.web.ConnectInterceptor;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.request.WebRequest;

public class FacebookConnectInterceptor implements ConnectInterceptor<Facebook> {

     public void preConnect(ConnectionFactory<Facebook> provider, MultiValueMap<String, String> parameters, WebRequest request) {
         System.out.println("before");
    }

    public void postConnect(Connection<Facebook> connection, WebRequest request) {
        System.out.println("I've connected with the Spring Social Showcase!");
        connection.updateStatus("I've connected with the Spring Social Showcase!");
    }

}
