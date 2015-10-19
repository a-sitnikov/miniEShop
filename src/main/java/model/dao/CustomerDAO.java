package model.dao;

import model.pojo.Customer;

public interface CustomerDAO {
    void add(Customer customer);
    void delete(String email);
    Customer getCustomer(String email);
}
