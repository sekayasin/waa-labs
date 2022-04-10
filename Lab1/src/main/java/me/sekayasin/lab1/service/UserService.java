package me.sekayasin.lab1.service;

import me.sekayasin.lab1.domain.Comment;
import me.sekayasin.lab1.domain.Role;
import me.sekayasin.lab1.domain.dto.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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

    Role saveRole(Role role);

    void addRoleToUser(String username, String roleName);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
