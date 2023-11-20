package com.joaosilveira.injectiondependencychallange.services;

import com.joaosilveira.injectiondependencychallange.entities.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {

    @Autowired
    private Order order;

    public double shipment(Order order) {

        if (order.getBasic() < 100.0) {
            return 20.0;
        } else if (order.getBasic() <= 100 && order.getBasic() > 200.0 ) {
            return 12.0;
        } else {
            return 0;
        }

    }

}
