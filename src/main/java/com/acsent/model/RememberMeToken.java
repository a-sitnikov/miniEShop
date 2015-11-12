package com.acsent.model;

import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "persistent_logins",
       indexes = @Index(name = "idx_username", columnList = "username")
)
public class RememberMeToken {

    @Column(length = 100)
    private String username;

    @Id
    @Column(length = 64)
    private String series;

    @Column(name="token", length = 64)
    private String tokenValue;

    @Column(name = "last_used")
    private Date date;

    public RememberMeToken() {
    }

    public RememberMeToken(PersistentRememberMeToken persistentRememberMeToken) {
        this.username   = persistentRememberMeToken.getUsername();
        this.series     = persistentRememberMeToken.getSeries();
        this.tokenValue = persistentRememberMeToken.getTokenValue();
        this.date       = persistentRememberMeToken.getDate();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getTokenValue() {
        return tokenValue;
    }

    public void setTokenValue(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
