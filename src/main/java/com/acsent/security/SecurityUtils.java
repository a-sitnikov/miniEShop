package com.acsent.security;

import com.acsent.model.AppUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.RememberMeServices;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public static void logInUser(AppUser appUser, HttpServletRequest request, HttpServletResponse response, RememberMeServices rememberMeServices) {

        AppSpringUser user = buildUserForAuthentication(appUser);
        Authentication auth = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());

        SecurityContext securityContext = SecurityContextHolder.getContext();
        securityContext.setAuthentication(auth);

        // Create a new session and add the security context.
        HttpSession session = request.getSession(true);
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext);

        rememberMeServices.loginSuccess(request, response, auth);
    }
}
