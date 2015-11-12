package com.acsent.security;

import com.acsent.model.AppUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class AppSpringUser extends User {

    private AppUser appUser;

    public AppSpringUser(String username, String password, Collection<? extends GrantedAuthority> authorities, AppUser appUser) {

        super(username, password, authorities);
        this.appUser = appUser;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }
}


