package com.acsent.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    private Long id;

    @Column
    private Date date;

    @ManyToOne
    @JoinColumn(name ="customer_id")
    private Customer customer;

    public Order() {
    }

    public Order(Long id, Customer customer, Date date) {
        this.id = id;
        this.customer = customer;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
