package com.acsent.security;

import com.acsent.model.AppUser;
import com.acsent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly=true)
    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {

        AppUser appUser = userRepository.findByUsername(username);
        if(appUser == null) {
            throw new UsernameNotFoundException("Could not find user " + username);
        }

        return buildUserForAuthentication(appUser);

    }

    private User buildUserForAuthentication(AppUser appUser) {

        List<GrantedAuthority> authorities = buildUserAuthority(appUser);
        return new AppSpringUser(appUser.getUsername(), appUser.getPassword(), authorities, appUser);

    }

    private List<GrantedAuthority> buildUserAuthority(AppUser appUser) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        setAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
        if (appUser.isAdmin()) {
            setAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }

        return new ArrayList<GrantedAuthority>(setAuths);
    }

}