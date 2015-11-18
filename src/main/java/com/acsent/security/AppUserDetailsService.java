package com.acsent.security;

import com.acsent.model.AppUser;
import com.acsent.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly=true)
    @Override
    public UserDetails loadUserByUsername(final String username)
            throws UsernameNotFoundException {

        AppUser appUser = userRepository.findOne(Long.decode(username));
        if(appUser == null) {
            throw new UsernameNotFoundException("Could not find user " + username);
        }

        return SecurityUtils.buildUserForAuthentication(appUser);

    }

}