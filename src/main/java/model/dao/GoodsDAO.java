package model.dao;

import model.pojo.Goods;

public interface GoodsDAO {
    void Add(Goods goods);
    void Delete(Goods goods);
}
