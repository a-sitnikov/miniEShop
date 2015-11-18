package com.acsent.repository;

import com.acsent.model.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<AppUser, Long> {
    AppUser findByEmail(String email);
    AppUser findByFacebookId(String facebookId);
}

