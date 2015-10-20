package model.impl;

import model.dao.ItemDAO;
import model.pojo.Item;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
class ItemDAOimpl implements ItemDAO {

    @Autowired
    private SessionFactory session;

    @Override
    public void add(Item item) {
        session.getCurrentSession().save(item);
    }

    @Override
    public void edit(Item item) {
        session.getCurrentSession().update(item);
}

    @Override
    public void delete(UUID id) {
        Item item = getItem(id);
        session.getCurrentSession().delete(item);
    }

    @Override
    public void delete(Item item) {
        session.getCurrentSession().delete(item);
    }

    @Override
    public Item getItem(UUID id) {
        return (Item)session.getCurrentSession().get(Item.class, id);
    }
}