package com.acsent.security;

import com.acsent.model.AppUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import java.util.*;

public class SecurityUtils {

    public static AppUser getAppUser() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // default userName = "anonymousUser"
        Object user = auth.getPrincipal();
        AppUser appUser = null;
        if (user instanceof AppSpringUser) {
            appUser = ((AppSpringUser)user).getAppUser();
        }

        return appUser;
    }

    public static AppSpringUser buildUserForAuthentication(AppUser appUser) {

        List<GrantedAuthority> authorities = buildUserAuthority(appUser);
        return new AppSpringUser(appUser.getUsername(), appUser.getPassword(), authorities, appUser);

    }

    public static List<GrantedAuthority> buildUserAuthority(AppUser appUser) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        setAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
        if (appUser.isAdmin()) {
            setAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return new ArrayList<GrantedAuthority>(setAuths);
    }

    public void logInUser(AppUser appUser) {

        AppSpringUser user = buildUserForAuthentication(appUser);
        Authentication auth = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(auth);

    }
}
