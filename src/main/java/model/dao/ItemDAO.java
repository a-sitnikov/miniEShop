package model.dao;

import model.pojo.Item;

import java.util.UUID;

public interface ItemDAO {
    void add(Item item);
    void delete(UUID itemID);
    Item getItem(UUID itemID);
}
