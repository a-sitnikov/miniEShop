package model.dao;

import model.pojo.Item;

import java.util.UUID;

public interface ItemDAO {
    void add(Item item);
    void edit(Item item);
    void delete(Item item);
    void delete(UUID id);
    Item getItem(UUID id);
}
