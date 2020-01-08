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

    private UserRepository groupRepository;

    public UserController(UserRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    //전체 조회
    @GetMapping("/groups")
    Collection<User> groups() {
        return groupRepository.findAll();
    }

    //상세조회
    @GetMapping("/group/{id}")
    ResponseEntity<?> getGroup(@PathVariable Long id) {
        Optional<User> group = groupRepository.findById(id);
        return group.map(response -> ResponseEntity.ok().body(response))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    //등록
    @PostMapping("/group/{id}")
    ResponseEntity<User> createGroup(@Valid @RequestBody User group) throws URISyntaxException {
        User result = groupRepository.save(group);
        return ResponseEntity.created(new URI("/api/group/" + result.getId()))
                .body(result);
    }

    //수정
    @PutMapping("/group/{id}")
    ResponseEntity<User> updateGroup(@Valid @RequestBody User group) {
        User result = groupRepository.save(group);
        return ResponseEntity.ok().body(result);
    }

    //삭제
    @DeleteMapping("/group/{id}")
    public ResponseEntity<?> deleteGroup(@PathVariable Long id) {
        groupRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}