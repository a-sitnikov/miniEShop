package com.acsent.model;

import javax.persistence.*;

@Entity
@Table(name = "items",
    indexes = @Index(name = "idx_category", columnList = "category_id")
)
public class Item {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(length = 100)
    private String name;

    @Column(columnDefinition = "Decimal(15,2)")
    private Float price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="category_id")
    private Category category;

    public Item() {
    }

    public Item(Long id, String name) {
        this.id       = id;
        this.name     = name;
    }

    public Item(Long id, String name, Category category) {
        this.id       = id;
        this.name     = name;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getImage() {
        return String.format("%02d", id);
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}

