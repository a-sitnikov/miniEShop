package com.acsent.repository;

import com.acsent.model.AppUser;
import com.acsent.model.CartDetail;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CartDetailRepository extends CrudRepository<CartDetail, Long> {

    //Hibernate:
    // select count(cartdetail0_.id) as col_0_0_
    // from cart cartdetail0_
    // left outer join users appuser1_ on cartdetail0_.user_id=appuser1_.id
    // where appuser1_.id=?
    //to prevent unnecessary left join
    @Query("SELECT count(*) FROM CartDetail as tab WHERE tab.appUser = :user")
    Long countByAppUser(@Param("user") AppUser appUser);

    @Query("SELECT sum(tab.sum) FROM CartDetail as tab WHERE tab.appUser = :user")
    Float sumByAppUser(@Param("user") AppUser appUser);

    ArrayList<CartDetail> findByAppUser(AppUser appUser);
}
