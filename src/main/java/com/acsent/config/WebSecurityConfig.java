package com.acsent.config;

import com.acsent.repository.RememberMeTokenRepository;
import com.acsent.security.PersistentTokenRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebMvcSecurity
@ComponentScan(basePackages = {"com.acsent"})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static PasswordEncoder encoder;

    @Autowired
    private UserDetailsService customUserDetailsService;

    @Autowired
    private RememberMeTokenRepository rememberMeTokenRepository;

    @Value("${application.remember-me.key}")
    private String rememberMeKey;// = "miniEShop";

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //PersistentTokenRepository tokenRepository = persistentTokenRepository();

        http
            .authorizeRequests()
                .anyRequest()
                .permitAll()
            .and()
            .formLogin()
                .loginPage("/login")
            .and()
            .logout()
                // GET logout (default POST)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
            .and()
            .rememberMe()
                .key(rememberMeKey)
                .rememberMeServices(getRememberMeServices());
    }

    //http://shruubi.com/2014/12/03/spring-boot-hibernate-and-spring-security-a-step-in-the-right-direction-for-java/
    //http://kielczewski.eu/2014/12/spring-boot-security-application/
    //http://stackoverflow.com/questions/7900994/programmatically-login-in-a-user-using-spring-security

    //http://blog.solidcraft.eu/2011/03/spring-security-by-example-user-in.html
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(customUserDetailsService)
            .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        return new PersistentTokenRepositoryImpl(rememberMeTokenRepository);
    }

    @Bean
    public RememberMeServices getRememberMeServices() {
        PersistentTokenBasedRememberMeServices rememberMeServices = new PersistentTokenBasedRememberMeServices(rememberMeKey, customUserDetailsService, persistentTokenRepository());
        rememberMeServices.setAlwaysRemember(true);
        return rememberMeServices;
    }
}