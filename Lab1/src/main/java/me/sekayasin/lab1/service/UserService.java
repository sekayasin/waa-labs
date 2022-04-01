package me.sekayasin.lab1.service;

import me.sekayasin.lab1.domain.dto.PostDto;
import me.sekayasin.lab1.domain.dto.PostResponseDto;
import me.sekayasin.lab1.domain.dto.UserDto;
import me.sekayasin.lab1.domain.dto.UserNameDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAll();

    UserDto findById(Long id);

    void save(UserNameDto user);

    void addPostByUserId(long id, PostDto postDto);

    List<PostResponseDto> findAllPostsByUserId(long id);
}
