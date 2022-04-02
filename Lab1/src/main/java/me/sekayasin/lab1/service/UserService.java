package me.sekayasin.lab1.service;

import me.sekayasin.lab1.domain.Comment;
import me.sekayasin.lab1.domain.dto.*;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();

    UserDto findById(Long id);

    void save(UserNameDto user);

    void addPostByUserId(long id, PostDto postDto);

    List<PostResponseDto> findAllPostsByUserId(long id);

    void deleteUserById(long id);

    List<UserDto> findAllUsersWithMoreThanNPosts(int numberOfPosts);

    CommentDto getUserCommentOnPostById(long userId, long postId, long commentId);

    List<Comment> getUserCommentsOnPost(long userId, long postId);

    List<UserDto> findUsersByPostTitle(String title);
}
