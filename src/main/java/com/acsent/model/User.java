package com.acsent.model;


import com.acsent.utils.PasswordCrypto;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column
    private String username;

    @Column(length = 250, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column
    private boolean enabled;

    @OneToMany(mappedBy = "user")
    private Set<UserRole> roles;

    public User(String username, String password) {

        this.username = username;
        this.email    = email;
        this.password = PasswordCrypto.getInstance().encrypt(password);

        if(this.roles == null) {
            this.roles = new HashSet<UserRole>();
        }

        //create a new user with basic user privileges
        this.roles.add(
                new UserRole(
                        RoleEnum.USER.toString(),
                        this
                ));

    }

    public User() {
        this.enabled = true;

        if(this.roles == null) {
            this.roles = new HashSet<UserRole>();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
