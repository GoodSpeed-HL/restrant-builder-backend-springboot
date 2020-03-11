package com.ucareer.builder.test;

import javax.persistence.*;
import java.util.Set;

@Entity
public class StoreOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String invoice;

//    @OneToMany(mappedBy = "storeOrder")
//    private Set<StoreItem> orderStoreItems;


    @OneToMany()
    @JoinColumn(name = "storeOrder")
    private Set<StoreItem> orderStoreItems;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public Set<StoreItem> getOrderStoreItems() {
        return orderStoreItems;
    }

    public void setOrderStoreItems(Set<StoreItem> orderStoreItems) {
        this.orderStoreItems = orderStoreItems;
    }
}
