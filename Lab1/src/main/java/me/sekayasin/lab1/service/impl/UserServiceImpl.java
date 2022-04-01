package me.sekayasin.lab1.service.impl;

import me.sekayasin.lab1.domain.Post;
import me.sekayasin.lab1.domain.User;
import me.sekayasin.lab1.domain.dto.PostDto;
import me.sekayasin.lab1.domain.dto.PostResponseDto;
import me.sekayasin.lab1.domain.dto.UserDto;
import me.sekayasin.lab1.domain.dto.UserNameDto;
import me.sekayasin.lab1.repo.UserRepo;
import me.sekayasin.lab1.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ModelMapper modelMapper;

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
        List<Post> postList = new ArrayList<>();
        postList.add(newPost);
        userToAddPost.setPosts(postList);
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
}
