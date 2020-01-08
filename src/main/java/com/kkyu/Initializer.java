//package com.kkyu;
//
//import java.util.Collections;
//import java.util.stream.Stream;
//
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import com.kkyu.model.Order;
//import com.kkyu.model.User;
//import com.kkyu.model.UserRepository;
//
//
//@Component
//class Initializer implements CommandLineRunner {
//
//    private final UserRepository repository;
//
//    public Initializer(UserRepository repository) {
//        this.repository = repository;
//    }
//
//    @Override
//    public void run(String... strings) {
//        Stream.of("한규영", "홍길동", "강감찬").forEach(name ->
//                repository.save(new User(name))
//        );
//
//        User user = repository.findByName("한규영");
//        Order order = Order.builder().orderName("커피")
//                .price(2000)
//                .build();
//        user.setOrders(Collections.singleton(order));
//        repository.save(user);
//
//        repository.findAll().forEach(System.out::println);
//    }
//}
