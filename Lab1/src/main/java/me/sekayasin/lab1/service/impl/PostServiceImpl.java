package me.sekayasin.lab1.service.impl;

import me.sekayasin.lab1.domain.Post;
import me.sekayasin.lab1.domain.dto.Content;
import me.sekayasin.lab1.domain.dto.ContentDto;
import me.sekayasin.lab1.domain.dto.PostDto;
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
    ListMapper<Post, PostDto> listMapperPostToDto;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    PostRepo postRepo;

    @Override
    public List<PostDto> findAll() {
        return (List<PostDto>) listMapperPostToDto.mapList(postRepo.findAll(), new PostDto());

//        return postRepo.findAll()
//                .stream()
//                .map(post -> modelMapper.map(post, PostDto.class))
//                .collect(Collectors.toList());
    }

    @Override
    public PostDto findById(long id) {
        return modelMapper.map(postRepo.findById(id), PostDto.class);
    }

    @Override
    public void save(PostDto post) {
        postRepo.save(modelMapper.map(post, Post.class));
    }

    @Override
    public void update(long id, ContentDto contentDto) {
        postRepo.update(id, modelMapper.map(contentDto, Post.class));
    }

    @Override
    public void delete(long id) {
        postRepo.delete(id);
    }

    @Override
    public Content getContentByPostId(long id) {
        return modelMapper.map(postRepo.findById(id), Content.class);
    }
}
