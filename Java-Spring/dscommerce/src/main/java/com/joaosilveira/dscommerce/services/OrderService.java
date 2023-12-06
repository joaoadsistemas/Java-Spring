package com.joaosilveira.dscommerce.services;

import com.joaosilveira.dscommerce.dto.OrderDTO;
import com.joaosilveira.dscommerce.dto.ProductDTO;
import com.joaosilveira.dscommerce.entities.Order;
import com.joaosilveira.dscommerce.entities.Product;
import com.joaosilveira.dscommerce.repositories.OrderRepository;
import com.joaosilveira.dscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Optional<Order> result = orderRepository.findById(id);
        Order product = result.orElseThrow(
                () -> new ResourceNotFoundException("Recurso nao encontrado")
        );
        OrderDTO dto = new OrderDTO(product);
        return dto;
    }


}
