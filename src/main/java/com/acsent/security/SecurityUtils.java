package com.acsent.security;

import com.acsent.model.AppUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by msk5446 on 12.11.2015.
 */
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

}
