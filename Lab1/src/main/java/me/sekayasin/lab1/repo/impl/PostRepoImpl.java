package me.sekayasin.lab1.repo.impl;

import me.sekayasin.lab1.domain.Post;
import me.sekayasin.lab1.domain.dto.Content;
import me.sekayasin.lab1.repo.PostRepo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PostRepoImpl implements PostRepo {
    private static final List<Post> posts;
    private static long postId = 300;
    static {
        posts = new ArrayList<>();

        Post post1 = new Post(101, "How to train your Dragon", "This is how we do it", "Jonah Hill");
        Post post2 = new Post(201, "Learn Java in 10 minutes", "This is how we do it", "Josh Bloch");
        Post post3 = new Post(102, "K8s the hard-way", "This is how we do it", "Kelsey High-tower");

        posts.add(post1);
        posts.add(post2);
        posts.add(post3);
    }

    @Override
    public List<Post> findAll() {
        return posts;
    }

    @Override
    public Post findById(long id) {
        return posts
                .stream()
                .filter(post -> post.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(Post post) {
        post.setId(postId);
        postId++;
        posts.add(post);
    }

    @Override
    public void update(long id, Post post) {
        Post postToUpdate = findById(id);
        postToUpdate.setTitle(post.getTitle());
        postToUpdate.setContent(post.getContent());
    }

    @Override
    public void delete(long id) {
        posts
                .stream()
                .filter(post -> post.getId() == id)
                .findFirst().ifPresent(posts::remove);
    }
}
