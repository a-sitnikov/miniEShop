package com.acsent.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders",
        indexes = @Index(name = "idx_customer",  columnList = "customer_id")
)
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
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
