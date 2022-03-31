package me.sekayasin.lab1.repo;

import me.sekayasin.lab1.domain.Post;
import me.sekayasin.lab1.domain.PostV2;

import java.util.List;

public interface PostRepo {
    List<Post> findAll();

    List<PostV2> findAllV2();

    Post findById(long id);

    List<Post> findByAuthor(String author);

    void save(Post post);

    void update(long id, Post post);

    void delete(long id);
}
