package me.sekayasin.lab1.controller;

import me.sekayasin.lab1.aspect.annotations.ExecutionTime;
import me.sekayasin.lab1.domain.Comment;
import me.sekayasin.lab1.domain.dto.*;
import me.sekayasin.lab1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<UserDto> findAll() {
        return userService.findAll();
    }
    
    @ResponseStatus(HttpStatus.OK)
    @ExecutionTime
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

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable long id) {
        userService.deleteUserById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/filter")
    public List<UserDto> findAllUsersWithMoreThanNPosts(@RequestParam(name = "posts", required = false) int numberOfPosts) {
        return userService.findAllUsersWithMoreThanNPosts(numberOfPosts);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/filters")
    public List<UserDto> findUsersByPostTitle(@RequestParam(name = "title", required = false) String title) {
        return userService.findUsersByPostTitle(title);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userId}/posts/{postId}/comments/{commentId}")
    public CommentDto getUserCommentOnPostById(@PathVariable long userId, @PathVariable long postId, @PathVariable long commentId){
        return userService.getUserCommentOnPostById(userId, postId, commentId);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{userId}/posts/{postId}/comments")
    public List<Comment> getUserCommentsOnPost(@PathVariable long userId, @PathVariable long postId){
        return userService.getUserCommentsOnPost(userId, postId);
    }

}
