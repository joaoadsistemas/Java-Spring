package com.joaosilveira.projectOrders.config;

import com.joaosilveira.projectOrders.entities.*;
import com.joaosilveira.projectOrders.entities.enums.OrderStatus;
import com.joaosilveira.projectOrders.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public void run(String... args) throws Exception {
        User u1 = new User(null, "Maira", "maira@gmail.com", "993939393", "213523");
        User u2 = new User(null, "Lucas", "lucas@gmail.com", "28104827871", "2135432");
        userRepository.saveAll(Arrays.asList(u1,u2));


        Order o1 = new Order(null, Instant.parse("2023-02-11T20:23:44Z"), OrderStatus.PAID, u1);
        Order o2 = new Order(null, Instant.parse("2023-04-12T00:33:44Z"), OrderStatus.WAITING_PAYMENT, u1);
        Order o3 = new Order(null, Instant.parse("2023-11-30T21:08:44Z"), OrderStatus.WAITING_PAYMENT, u2);
        orderRepository.saveAll(Arrays.asList(o1,o2,o3));

        Category cat1 = new Category(null, "Eletronics");
        Category cat2 = new Category(null, "Books");
        Category cat3 = new Category(null, "Computers");
        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));


        Product p1 = new Product(null, "The Lord of Rings", "lorem ipsum dolor sit amet, consectur.", 90.4, "");
        Product p2 = new Product(null, "Smart TV", "lorem ipsum dolor sit amet, consectur.", 21980.0, "");
        Product p3 = new Product(null, "Mac Book Pro", "lorem ipsum dolor sit amet, consectur.", 1250.0, "");
        Product p4 = new Product(null, "PC Gamer", "lorem ipsum dolor sit amet, consectur.", 1200.0, "");
        Product p5 = new Product(null, "Rails for Dummies", "lorem ipsum dolor sit amet, consectur.", 100.90, "");
        productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));

        // FAZENDO O RELACIONAMENTO MUITOS PARA MUITOS

        p1.getCategories().add(cat2);
        p2.getCategories().add(cat1);
        p2.getCategories().add(cat3);
        p3.getCategories().add(cat3);
        p4.getCategories().add(cat3);
        p5.getCategories().add(cat2);

        productRepository.saveAll(Arrays.asList(p1,p2,p3,p4,p5));

        //



        OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
        OrderItem oi2 = new OrderItem(o1, p3, 1, p4.getPrice());
        OrderItem oi3 = new OrderItem(o2, p3, 2, p1.getPrice());
        OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());

        orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));

        // UM PARA UM
        Payment pay1 = new Payment(null, Instant.parse("2023-02-11T22:23:44Z"), o1);
        o1.setPayment(pay1);

        orderRepository.save(o1);
        //

    }
}
