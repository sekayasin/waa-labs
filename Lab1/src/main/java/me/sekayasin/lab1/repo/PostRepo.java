package me.sekayasin.lab1.repo;

import me.sekayasin.lab1.domain.Post;
import me.sekayasin.lab1.domain.dto.Content;
import me.sekayasin.lab1.domain.dto.ContentDto;

import java.util.List;

public interface PostRepo {
    List<Post> findAll();

    Post findById(long id);

    void save(Post post);

    void update(long id, Post post);

    void delete(long id);
}
