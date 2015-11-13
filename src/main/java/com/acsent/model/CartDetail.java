package com.acsent.model;

import javax.persistence.*;

@Entity
@Table(name="cart",
    indexes = @Index(name = "idx_user", columnList = "user_id")
)
public class CartDetail {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private AppUser appUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="item_id")
    private Item item;

    @Column(columnDefinition = "Decimal(15,2)", nullable = false)
    private Float price;

    @Column(nullable = false)
    private Integer quantity;

    @Column(columnDefinition = "Decimal(15,2)", nullable = false)
    private Float sum;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppUser getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUser appUser) {
        this.appUser = appUser;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getSum() {
        return sum;
    }

    public void setSum(Float sum) {
        this.sum = sum;
    }
}
