package com.kkyu;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.kkyu.model.Order;
import com.kkyu.model.User;
import com.kkyu.model.UserRepository;


@Component
class Initializer implements CommandLineRunner {

    private final UserRepository repository;

    public Initializer(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... strings) {
        Stream.of("한규영", "홍길동", "강감찬").forEach(name ->
                repository.save(new User(name))
        );

        User user = repository.findByName("한규영");
        Order order = Order.builder().orderName("아메리카노")
                .price(900)
                .build();
        
        Order order2 = Order.builder().orderName("카페라떼")
                .price(1200)
                .build();

        Set<Order> orders = new HashSet<>();
        orders.add(order);
        orders.add(order2);
        
        user.setOrders(orders);
        
        repository.save(user);

    }
}
