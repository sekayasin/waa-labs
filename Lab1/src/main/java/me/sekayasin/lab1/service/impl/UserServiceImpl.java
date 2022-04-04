package me.sekayasin.lab1.service.impl;

import me.sekayasin.lab1.domain.Comment;
import me.sekayasin.lab1.domain.Post;
import me.sekayasin.lab1.domain.User;
import me.sekayasin.lab1.domain.dto.*;
import me.sekayasin.lab1.repo.UserRepo;
import me.sekayasin.lab1.service.UserService;
import me.sekayasin.lab1.util.ListMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;
    private final ListMapper<User, UserDto> listMapperUserDto;

    @Autowired
    public UserServiceImpl(UserRepo userRepo, ModelMapper modelMapper, ListMapper<User, UserDto> listMapperUserDto) {
        this.userRepo = userRepo;
        this.modelMapper = modelMapper;
        this.listMapperUserDto = listMapperUserDto;
    }

    @Override
    public List<UserDto> findAll() {
        return userRepo.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Long id) {
        if (!userRepo.existsById(id))
            throw new IllegalStateException("User with Id " + id + " does not exist");
        return modelMapper.map(userRepo.getById(id), UserDto.class);
    }

    @Override
    public void save(UserNameDto user) {
        userRepo.save(modelMapper.map(user, User.class));
    }

    @Override
    public void addPostByUserId(long id, PostDto postDto) {
        if (!userRepo.existsById(id))
            throw new IllegalStateException("User with Id " + id + " does not exist");
        User userToAddPost = userRepo.getById(id);
        Post newPost = modelMapper.map(postDto, Post.class);
        userToAddPost.getPosts().add(newPost);
        userRepo.save(userToAddPost);
    }

    @Override
    public List<PostResponseDto> findAllPostsByUserId(long id) {
        if (!userRepo.existsById(id))
            throw new IllegalStateException("User with Id " + id + " does not exist");
        return userRepo.getById(id).getPosts()
                .stream()
                .map(post -> modelMapper.map(post, PostResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUserById(long id) {
        if (!userRepo.existsById(id))
            throw new IllegalStateException("User with Id " + id + " does not exist");
        userRepo.deleteById(id);
    }

    @Override
    public List<UserDto> findAllUsersWithMoreThanNPosts(int numberOfPosts) {
        return (List<UserDto>) listMapperUserDto.mapList(userRepo.findAllUsersWithMoreThanNPosts(numberOfPosts), new UserDto());

//        return userRepo.findAll()
//                .stream()
//                .filter(user -> user.getPosts().size() > numberOfPosts)
//                .map(user -> modelMapper.map(user, UserDto.class))
//                .collect(Collectors.toList());
    }

    @Override
    public CommentDto getUserCommentOnPostById(long userId, long postId, long commentId) {
        if (!userRepo.existsById(userId))
            throw new IllegalStateException("User with Id " + userId + " does not exist");

        List<Post> postList = userRepo.getById(userId).getPosts();

        List<Comment> commentList = postList.stream()
                .filter(post -> post.getId() == postId)
                .map(Post::getComments)
                .collect(Collectors.toList())
                .stream().flatMap(Collection::stream)
                .collect(Collectors.toList());

        for (Comment comment : commentList) {
            if (comment.getId() == commentId)
                return modelMapper.map(comment, CommentDto.class);
        }
        return null;
    }

    @Override
    public List<Comment> getUserCommentsOnPost(long userId, long postId) {
        if (!userRepo.existsById(userId))
            throw new IllegalStateException("User with Id " + userId + " does not exist");
        return userRepo.getById(userId).getPosts()
                .stream()
                .filter(post -> post.getId() == postId)
                .map(Post::getComments)
                .collect(Collectors.toList())
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> findUsersByPostTitle(String title) {
        return (List<UserDto>) listMapperUserDto.mapList(userRepo.findUsersByPostTitle(title), new UserDto());
    }
}
