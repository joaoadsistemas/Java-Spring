package com.joaosilveira.dscommerce.services;

import com.joaosilveira.dscommerce.dto.OrderDTO;
import com.joaosilveira.dscommerce.entities.Order;
import com.joaosilveira.dscommerce.entities.OrderItem;
import com.joaosilveira.dscommerce.entities.Product;
import com.joaosilveira.dscommerce.entities.User;
import com.joaosilveira.dscommerce.repositories.OrderItemRepository;
import com.joaosilveira.dscommerce.repositories.OrderRepository;
import com.joaosilveira.dscommerce.repositories.ProductRepository;
import com.joaosilveira.dscommerce.services.exceptions.ForbiddenException;
import com.joaosilveira.dscommerce.services.exceptions.ResourceNotFoundException;
import com.joaosilveira.dscommerce.tests.OrderFactory;
import com.joaosilveira.dscommerce.tests.ProductFactory;
import com.joaosilveira.dscommerce.tests.UserFactory;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class OrderServiceTests {

    @InjectMocks
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private AuthService authService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private OrderItemRepository orderItemRepository;

    @Mock
    private UserService userService;

    private Long existsId, nonExistsId;
    private Order order;
    private OrderDTO orderDTO;
    private User admin, client;
    private Product product;

    @BeforeEach
    void setUp() throws Exception {
        existsId = 1L;
        nonExistsId = 2L;

        admin = UserFactory.createCustomAdminUser(1L, "Jeff");
        client = UserFactory.createCustomClientUser(2L, "Bob");

        order = OrderFactory.createOrder(client);

        orderDTO = new OrderDTO(order);

        product = ProductFactory.createProduct();

        Mockito.when(orderRepository.findById(existsId)).thenReturn(Optional.of(order));
        Mockito.when(orderRepository.findById(nonExistsId)).thenReturn(Optional.empty());

        Mockito.when(productRepository.getReferenceById(existsId)).thenReturn(product);
        Mockito.when(productRepository.getReferenceById(nonExistsId)).thenThrow(EntityNotFoundException.class);

        Mockito.when(orderRepository.save(any())).thenReturn(order);

        Mockito.when(orderItemRepository.saveAll(any())).thenReturn(new ArrayList<>(order.getItems()));

    }


    @Test
    public void findByIdShouldReturnOrderDTOWhenIdExistsAndAdminLogged() {

        Mockito.doNothing().when(authService).validateSelfOrAdmin(any());

        OrderDTO result = orderService.findById(existsId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getId(), existsId);

    }

    @Test
    public void findByIdShouldReturnOrderDTOWhenIdExistsAndSelfLogged() {

        Mockito.doNothing().when(authService).validateSelfOrAdmin(any());

        OrderDTO result = orderService.findById(existsId);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getId(), existsId);

    }

    @Test
    public void findByIdShouldThrowsForbiddenExceptionWhenIdExistsAndOtherClientLogged() {

        Mockito.doThrow(ForbiddenException.class).when(authService).validateSelfOrAdmin(any());

        Assertions.assertThrows(ForbiddenException.class, () -> {
           OrderDTO result = orderService.findById(existsId);
        });
    }

    @Test
    public void findByIdShouldThrowResourceNotFoundExceptionWhenIdDoesNotExists() {

        Mockito.doNothing().when(authService).validateSelfOrAdmin(any());

        Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            OrderDTO result = orderService.findById(nonExistsId);
        });

    }

    @Test
    public void insertShouldReturnOrderDTOWhenAdminLogged() {

        Mockito.when(userService.authenticated()).thenReturn(admin);

        OrderDTO result = orderService.insert(orderDTO);

        Assertions.assertNotNull(result);
    }

    @Test
    public void insertShouldReturnOrderDTOWhenClientLogged() {

        Mockito.when(userService.authenticated()).thenReturn(client);

        OrderDTO result = orderService.insert(orderDTO);

        Assertions.assertNotNull(result);
    }


    @Test
    public void insertShouldThrowUsernameNotFoundExceptionWhenUserNotLogged() {

        Mockito.doThrow(UsernameNotFoundException.class).when(userService).authenticated();

        order.setClient(new User());
        orderDTO = new OrderDTO(order);

        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
           OrderDTO result = orderService.insert(orderDTO);
        });

    }

    @Test
    public void insertShouldThrowEntityNotFoundExceptionWhenOrderProductIdDoesNotExist() {

        Mockito.when(userService.authenticated()).thenReturn(client);

        product.setId(nonExistsId);
        OrderItem orderItem = new OrderItem(order, product, 2, 10.0);
        order.getItems().add(orderItem);

        orderDTO = new OrderDTO(order);

        Assertions.assertThrows(EntityNotFoundException.class, () -> {
           OrderDTO result = orderService.insert(orderDTO);
        });

    }
}
