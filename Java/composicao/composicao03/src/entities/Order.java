package entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private LocalDateTime moment;
    private OrderStatus status;
    private List<OrderItem> orderItems = new ArrayList<>();
    private Client client;


    public Order() {

    }

    public Order(LocalDateTime moment, OrderStatus status, Client client) {
        this.moment = moment;
        this.status = status;
        this.client = client;
    }

    public LocalDateTime getMoment() {
        return moment;
    }

    public void setMoment(LocalDateTime moment) {
        this.moment = moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void addOrdemItem (OrderItem orderItem) {
        orderItems.add(orderItem);
    }

    public void removeOrdemItem (OrderItem orderItem) {
        orderItems.remove(orderItem);
    }

    public double total() {
        double total = 0;
        for (OrderItem p: orderItems) {
            total += p.subTotal();
        }
        return total;
    }
}
