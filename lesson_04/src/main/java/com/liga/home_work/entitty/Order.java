package com.liga.home_work.entitty;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "table_orders")
public class Order {

    @Id
    private int id;

    @Column
    private String name;

    @Column
    private Integer price;

    @Column
    private int customer_id;

    public Order() {
    }

    public Order(int id, String name, Integer price, int customer_id) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.customer_id = customer_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id &&
                customer_id == order.customer_id &&
                Objects.equals(name, order.name) &&
                Objects.equals(price, order.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, customer_id);
    }
}
