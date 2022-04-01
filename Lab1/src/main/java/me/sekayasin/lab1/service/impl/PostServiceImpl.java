package me.sekayasin.lab1.service.impl;

import me.sekayasin.lab1.domain.Post;
import me.sekayasin.lab1.domain.dto.Content;
import me.sekayasin.lab1.domain.dto.ContentDto;
import me.sekayasin.lab1.domain.dto.PostDto;
import me.sekayasin.lab1.domain.dto.PostResponseDto;
import me.sekayasin.lab1.repo.PostRepo;
import me.sekayasin.lab1.service.PostService;
import me.sekayasin.lab1.util.ListMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    ListMapper<Post, PostResponseDto> listMapperPostToDto;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PostRepo postRepo;

    @Override
    public List<PostResponseDto> findAll() {
        return (List<PostResponseDto>) listMapperPostToDto.mapList(postRepo.findAll(), new PostResponseDto());
    }

//    @Override
//    public List<PostV2> findAllV2() {
//        return postRepo.findAllV2();
//    }

    @Override
    public List<PostResponseDto> findByAuthor(String author) {
        return findAll()
                .stream()
                .filter(postResponseDto -> postResponseDto.getAuthor().equals(author))
                .collect(Collectors.toList());
    }

    @Override
    public PostResponseDto findById(long id) {
        return modelMapper.map(postRepo.getById(id), PostResponseDto.class);
    }

    @Override
    public void save(PostDto post) {
        postRepo.save(modelMapper.map(post, Post.class));
    }

    @Override
    public void update(long id, ContentDto contentDto) {
        Post postToUpdate = postRepo.getById(id);
        postToUpdate.setTitle(contentDto.getTitle());
        postToUpdate.setContent(contentDto.getContent());
        postRepo.save(postToUpdate);
    }

    @Override
    public void delete(long id) {
        postRepo.deleteById(id);
    }

    @Override
    public Content getContentByPostId(long id) {
        return modelMapper.map(postRepo.getById(id), Content.class);
    }
}
