package com.acsent.model;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {

    @ManyToOne
    @JoinColumn(name ="order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name ="item_id")
    private Item item;

    @Column
    private float price;

    @Column
    private float qty;

    @Column
    private float sum;

    public OrderItem() {
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getQty() {
        return qty;
    }

    public void setQty(float qty) {
        this.qty = qty;
    }

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }
}
