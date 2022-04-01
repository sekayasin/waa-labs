package me.sekayasin.lab1.controller;

import me.sekayasin.lab1.domain.dto.Content;
import me.sekayasin.lab1.domain.dto.ContentDto;
import me.sekayasin.lab1.domain.dto.PostDto;
import me.sekayasin.lab1.domain.dto.PostResponseDto;
import me.sekayasin.lab1.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {

    @Autowired
    PostService postService;

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

    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getById(@PathVariable long id) {
        return ResponseEntity.ok(postService.findById(id));
    }

    @GetMapping("/filter")
    public List<PostResponseDto> getByAuthor(@RequestParam(required = false) String author){
        return postService.findByAuthor(author);
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




}
