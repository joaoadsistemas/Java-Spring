package com.joaosilveira.injectiondependencychallange.services;

import com.joaosilveira.injectiondependencychallange.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OderService {

    @Autowired
    private ShippingService shippingService;

    public double total(Order order) {
        return order.getBasic() - (order.getBasic() * order.getDiscount()) + shippingService.shipment(order);
    }

}
