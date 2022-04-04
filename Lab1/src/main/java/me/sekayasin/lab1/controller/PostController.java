package me.sekayasin.lab1.controller;

import me.sekayasin.lab1.domain.Comment;
import me.sekayasin.lab1.domain.dto.*;
import me.sekayasin.lab1.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@CrossOrigin(origins = {"http://localhost:3000"})
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public void save(@RequestBody PostDto post) {
        postService.save(post);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<PostResponseDto> getAll() {
        return postService.findAll();
    }

//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping(headers = "API-VERSION=1.1")
//    public List<PostV2> getAllV2() {
//        return postService.findAllV2();
//    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getById(@PathVariable long id) {
        return ResponseEntity.ok(postService.findById(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/filter")
    public List<PostResponseDto> getByAuthor(@RequestParam(name = "author", required = false) String author){
        return postService.findByAuthor(author);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/filters")
    public List<PostResponseDto> findPostsByTitle(@RequestParam(name = "title", required = false) String titleKeyWord) {
        return postService.findPostsByTitle(titleKeyWord);
    }

    @GetMapping("/{id}/content")
    public ResponseEntity<Content> getContentByPostId(@PathVariable long id) {
        return ResponseEntity.ok(postService.getContentByPostId(id));
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public void update(@PathVariable long id, @RequestBody ContentDto contentDto) {
        postService.update(id, contentDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        postService.delete(id);
    }

    @GetMapping("/{id}/comments")
    public List<Comment> getCommentsByPostId(@PathVariable long id) {
        return postService.getCommentsByPostId(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{id}/comments")
    public void addCommentByPostId(@PathVariable long id, @RequestBody CommentDto commentDto) {
        postService.addCommentByPostId(id, commentDto);
    }
}
