package com.kkyu.web;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kkyu.model.User;
import com.kkyu.model.UserRepository;

@RestController
@RequestMapping("/api")
class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    //전체 조회
    @GetMapping("/users")
    Collection<User> users() {
        return userRepository.findAll();
    }

    //상세조회
    @GetMapping("/user/{id}")
    ResponseEntity<?> getUser(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //등록
    @PostMapping("/user/{id}")
    ResponseEntity<User> createUser(@Valid @RequestBody User user) throws URISyntaxException {
    	User result = userRepository.save(user);
        return ResponseEntity.created(new URI("/api/user/" + result.getId()))
                .body(result);
    }

    //수정
    @PutMapping("/user/{id}")
    ResponseEntity<User> updateUser(@Valid @RequestBody User user) {
        User result = userRepository.save(user);
        return ResponseEntity.ok().body(result);
    }

    //삭제
    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
    	userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}