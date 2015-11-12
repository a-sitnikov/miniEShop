package com.acsent.security;

import com.acsent.model.RememberMeToken;
import com.acsent.repository.RememberMeTokenRepository;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import java.util.Date;
import java.util.List;

public class PersistentTokenRepositoryImpl implements PersistentTokenRepository {

    private final RememberMeTokenRepository rememberMeTokenRepository;

    public PersistentTokenRepositoryImpl(RememberMeTokenRepository rememberMeTokenRepository){
        this.rememberMeTokenRepository = rememberMeTokenRepository;
    }

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        RememberMeToken newToken = new RememberMeToken(token);
        rememberMeTokenRepository.save(newToken);
    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        RememberMeToken token = rememberMeTokenRepository.findBySeries(series);
        if (token != null){
            token.setTokenValue(tokenValue);
            token.setDate(lastUsed);
            rememberMeTokenRepository.save(token);
        }

    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {
        RememberMeToken token = rememberMeTokenRepository.findBySeries(seriesId);
        return new PersistentRememberMeToken(token.getUsername(), token.getSeries(), token.getTokenValue(), token.getDate());
    }

    @Override
    public void removeUserTokens(String username) {
        List<RememberMeToken> tokens = rememberMeTokenRepository.findByUsername(username);
        rememberMeTokenRepository.delete(tokens);
    }
}
