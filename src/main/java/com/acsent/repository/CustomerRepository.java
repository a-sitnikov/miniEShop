package com.acsent.repository;

import com.acsent.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer findByEmail(String email);
}

