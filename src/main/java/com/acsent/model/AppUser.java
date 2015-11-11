package com.acsent.model;


import com.acsent.utils.PasswordCrypto;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class AppUser {

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

    @Column(name="isAdmin")
    private boolean admin;

    public AppUser(String username, String password) {

        this.username = username;
        this.email    = email;
        this.password = PasswordCrypto.getInstance().encrypt(password);

    }

    public AppUser() {
        this.enabled = true;
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
        this.password = PasswordCrypto.getInstance().encrypt(password);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
