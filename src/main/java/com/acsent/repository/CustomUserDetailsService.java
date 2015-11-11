package com.acsent.repository;

import com.acsent.model.AppUser;
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
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly=true)
    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {

        AppUser appUser = userRepository.findByUsername(username);
        List<GrantedAuthority> authorities = buildUserAuthority(appUser);

        return buildUserForAuthentication(appUser, authorities);

    }

    private User buildUserForAuthentication(AppUser user, List<GrantedAuthority> authorities) {
        return new User(user.getUsername(), user.getPassword(), authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(AppUser appUser) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        setAuths.add(new SimpleGrantedAuthority("USER"));
        if (appUser.isAdmin()) {
            setAuths.add(new SimpleGrantedAuthority("ADMIN"));
        }

        return new ArrayList<GrantedAuthority>(setAuths);
    }
}