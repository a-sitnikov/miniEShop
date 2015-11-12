package com.acsent.repository;

import com.acsent.model.RememberMeToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RememberMeTokenRepository extends CrudRepository<RememberMeToken, Long> {
    RememberMeToken findBySeries(String series);
    List<RememberMeToken> findByUsername(String username);
}
