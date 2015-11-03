package com.acsent.repository;

import com.acsent.model.Item;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
@Qualifier(value = "ItemRepository")
public interface ItemRepository extends CrudRepository<Item, Long> {
    Item findByName(String name);
    Page<Item> findAll(Pageable pageable);
    ArrayList<Item> findAllByOrderByNameAsc(Pageable pageable);
}