package me.sekayasin.lab1.repo.impl;

import me.sekayasin.lab1.domain.Post;
import me.sekayasin.lab1.domain.PostV2;
import me.sekayasin.lab1.repo.PostRepo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PostRepoImpl implements PostRepo {
    private static final List<Post> posts;
    private static final List<PostV2> postsv2;
    private static long postId = 300;
    static {
        posts = new ArrayList<>();
        postsv2 = new ArrayList<>();

        Post post1 = new Post(101, "How to train your Dragon", "This is how we do it", "Jonah Hill");
        PostV2 post1v2 = new PostV2(100, "How to train your Dragon", "This is how we do it", "Jonah Hill");

        Post post2 = new Post(201, "Learn Java in 10 minutes", "This is how we do it", "Josh Bloch");
        Post post3 = new Post(102, "K8s the hard-way", "This is how we do it", "Kelsey High-tower");

        posts.add(post1);
        postsv2.add(post1v2);

        posts.add(post2);
        posts.add(post3);
    }

    @Override
    public List<Post> findAll() {
        return posts;
    }

    @Override
    public List<PostV2> findAllV2() {
        return postsv2;
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
    public List<Post> findByAuthor(String author) {
        return posts
                .stream()
                .filter(post -> post.getAuthor().equals(author))
                .collect(Collectors.toList());
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
