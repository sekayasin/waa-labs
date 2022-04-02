package me.sekayasin.lab1.service;

import me.sekayasin.lab1.domain.Comment;
import me.sekayasin.lab1.domain.Post;
import me.sekayasin.lab1.domain.dto.*;

import java.util.List;

public interface PostService {
    List<PostResponseDto> findAll();

//    List<PostV2> findAllV2();

    List<PostResponseDto> findByAuthor(String author);

    PostResponseDto findById(long id);

    void save(PostDto post);

    void update(long id, ContentDto contentDto);

    void delete(long id);

    Content getContentByPostId(long id);

    List<Comment> getCommentsByPostId(long id);

    void addCommentByPostId(long id, CommentDto commentDto);

    List<PostResponseDto> findPostsByTitle(String titleKeyWord);
}
