package com.joaosilveira.dscommerce.entities;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_order_item")
public class OrderItem {
    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();
    private Integer quantity;
    private Double price;

    public OrderItem() {

    }

    // em vez de atribuir no construtor um OrderItemPK, atribuir um Order e um product e dar set no ID (OrderItemPK)
    public OrderItem(Order order, Product product, Integer quantity, Double price) {
        id.setOrder(order);
        id.setProduct(product);
        this.quantity = quantity;
        this.price = price;
    }

    // usa um getOrder em vez de usar um getOrderItemPK
    public Order getOrder() {
        return id.getOrder();
    }

    // usa um setOrder em vez de usar um setOrderItemPK
    public void setOrder(Order order) {
        id.setOrder(order);
    }

    // usa um getProduct em vez de usar um getOrderItemPK
    public Product getProduct() {
        return id.getProduct();
    }

    // usa um setProduct em vez de usar um setProductItemPK
    public void setProduct(Product product) {
        id.setProduct(product);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
