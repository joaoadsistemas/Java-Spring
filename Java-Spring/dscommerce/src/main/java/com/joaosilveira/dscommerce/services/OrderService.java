package com.joaosilveira.dscommerce.services;

import com.joaosilveira.dscommerce.dto.OrderDTO;
import com.joaosilveira.dscommerce.dto.OrderItemDTO;
import com.joaosilveira.dscommerce.dto.ProductDTO;
import com.joaosilveira.dscommerce.entities.*;
import com.joaosilveira.dscommerce.repositories.OrderItemRepository;
import com.joaosilveira.dscommerce.repositories.OrderRepository;
import com.joaosilveira.dscommerce.repositories.ProductRepository;
import com.joaosilveira.dscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Transactional(readOnly = true)
    public OrderDTO findById(Long id) {
        Optional<Order> result = orderRepository.findById(id);
        Order product = result.orElseThrow(
                () -> new ResourceNotFoundException("Recurso nao encontrado")
        );
        OrderDTO dto = new OrderDTO(product);
        return dto;
    }


    @Transactional
    public OrderDTO insert(OrderDTO dto) {

        Order order = new Order();

        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);

        User user = userService.authenticated();
        order.setClient(user);

        for (OrderItemDTO itemDto: dto.getItems()) {
            Product product = productRepository.getReferenceById(itemDto.getProductId());
            OrderItem item = new OrderItem(order, product, itemDto.getQuantity(), product.getPrice());
            order.getItems().add(item);
        }

        orderRepository.save(order);
        orderItemRepository.saveAll(order.getItems());


        return new OrderDTO(order);
    }
}
