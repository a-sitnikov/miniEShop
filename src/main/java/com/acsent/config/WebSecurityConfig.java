package com.acsent.config;

import org.springframework.beans.factory.annotation.Autowired;
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


@Configuration
@EnableWebMvcSecurity
@ComponentScan(basePackages = {"com.acsent"})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static PasswordEncoder encoder;

    @Autowired
    private UserDetailsService customUserDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .authorizeRequests()
//            .antMatchers("/", "/login/home", "/login/create").permitAll()
//            .anyRequest().authenticated()
//            .and()
//            .formLogin()
//            .loginPage("/login/login")
//            .permitAll()
//            .and()
//            .logout()
//            .permitAll();

        http
            .authorizeRequests()
                .anyRequest()
                .permitAll()
            .and()
                .formLogin()
                .loginPage("/login")
            .and()
                .rememberMe()
                .key("miniEShop");
    }

    //http://shruubi.com/2014/12/03/spring-boot-hibernate-and-spring-security-a-step-in-the-right-direction-for-java/
    //http://kielczewski.eu/2014/12/spring-boot-security-application/
    //http://stackoverflow.com/questions/7900994/programmatically-login-in-a-user-using-spring-security

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
}