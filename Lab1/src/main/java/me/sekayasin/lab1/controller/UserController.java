package me.sekayasin.lab1.controller;

import me.sekayasin.lab1.domain.dto.PostDto;
import me.sekayasin.lab1.domain.dto.PostResponseDto;
import me.sekayasin.lab1.domain.dto.UserDto;
import me.sekayasin.lab1.domain.dto.UserNameDto;
import me.sekayasin.lab1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<UserDto> findAll() {
        return userService.findAll();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public UserDto findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}/posts")
    public List<PostResponseDto> findAllPostsByUserId(@PathVariable Long id) {
        return userService.findAllPostsByUserId(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void save(@RequestBody UserNameDto user) {
        userService.save(user);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/{id}/posts")
    public void addPostByUserId(@PathVariable long id, @RequestBody PostDto postDto) {
        userService.addPostByUserId(id, postDto);
    }


}
