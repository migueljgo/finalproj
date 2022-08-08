package com.csb.appadvc2122.model;

import javax.persistence.*;

@Entity
@Table(name = "ingredients")
public class IModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String item;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private String units;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    @Override
    public String toString() {
        return "IModel{" +
                "id=" + id +
                ", item='" + item + '\'' +
                ", quantity=" + quantity +
                ", units='" + units + '\'' +
                '}';
    }
}
