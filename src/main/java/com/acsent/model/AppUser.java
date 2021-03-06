package com.acsent.model;


import javax.persistence.*;

@Entity
@Table(name = "users",
    indexes = @Index(name = "idx_facebook", columnList = "facebookId")
)
public class AppUser {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(length = 250, unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(nullable = false)
    private Boolean enabled = true;

    @Column(name="isAdmin", nullable = false)
    private Boolean admin = false;

    @Column(name="isTemp", nullable = false)
    private Boolean temp = false;

    @Column(length = 50)
    private String facebookId;

    public AppUser() {
        this.enabled = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean isAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getFacebookId() {
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public Boolean getTemp() {
        return temp;
    }

    public void setTemp(Boolean temp) {
        this.temp = temp;
    }
}
