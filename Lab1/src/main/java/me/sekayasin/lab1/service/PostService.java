package me.sekayasin.lab1.service;

import me.sekayasin.lab1.domain.Post;
import me.sekayasin.lab1.domain.PostV2;
import me.sekayasin.lab1.domain.dto.Content;
import me.sekayasin.lab1.domain.dto.ContentDto;
import me.sekayasin.lab1.domain.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> findAll();

    List<PostV2> findAllV2();

    List<Post> findByAuthor(String author);

    PostDto findById(long id);

    void save(PostDto post);

    void update(long id, ContentDto contentDto);

    void delete(long id);

    Content getContentByPostId(long id);
}
