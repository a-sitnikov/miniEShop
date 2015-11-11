package com.acsent.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders",
        indexes = @Index(name = "idx_user",  columnList = "user_id")
)
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column
    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name ="user_id")
    private AppUser user;

    public Order() {
    }

    public Order(Long id, AppUser user, Date date) {
        this.id = id;
        this.user = user;
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

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
}
