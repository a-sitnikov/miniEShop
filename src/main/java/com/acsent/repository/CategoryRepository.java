package com.acsent.repository;

import com.acsent.model.Category;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.UUID;

@Repository
@Qualifier(value = "CategoryRepository")
public interface CategoryRepository extends CrudRepository<Category, UUID> {
    ArrayList<Category> findAll();
}