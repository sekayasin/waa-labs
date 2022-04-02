package me.sekayasin.lab1.service.impl;

import me.sekayasin.lab1.domain.Comment;
import me.sekayasin.lab1.domain.Post;
import me.sekayasin.lab1.domain.dto.*;
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

    private final ListMapper<Post, PostResponseDto> listMapperPostToDto;
    private final ModelMapper modelMapper;
    private final PostRepo postRepo;

    @Autowired
    public PostServiceImpl(ListMapper<Post, PostResponseDto> listMapperPostToDto, ModelMapper modelMapper, PostRepo postRepo) {
        this.listMapperPostToDto = listMapperPostToDto;
        this.modelMapper = modelMapper;
        this.postRepo = postRepo;
    }

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
        if (!postRepo.existsById(id))
            throw new IllegalStateException("Post with Id " + id + " does not exist");
        return modelMapper.map(postRepo.getById(id), PostResponseDto.class);
    }

    @Override
    public void save(PostDto post) {
        postRepo.save(modelMapper.map(post, Post.class));
    }

    @Override
    public void update(long id, ContentDto contentDto) {
        if (!postRepo.existsById(id))
            throw new IllegalStateException("Post with Id " + id + " does not exist");
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
        if (!postRepo.existsById(id))
            throw new IllegalStateException("Post with Id " + id + " does not exist");
        return modelMapper.map(postRepo.getById(id), Content.class);
    }

    @Override
    public List<Comment> getCommentsByPostId(long id) {
        if (!postRepo.existsById(id))
            throw new IllegalStateException("Post with Id " + id + " does not exist");
        return postRepo.getById(id).getComments();
    }

    @Override
    public void addCommentByPostId(long id, CommentDto commentDto) {
        if (!postRepo.existsById(id))
            throw new IllegalStateException("Post with Id " + id + " does not exist");
        Post postToAddComment = postRepo.getById(id);
        Comment newComment = modelMapper.map(commentDto, Comment.class);
        postToAddComment.getComments().add(newComment);
        postRepo.save(postToAddComment);
    }

    @Override
    public List<PostResponseDto> findPostsByTitle(String titleKeyWord) {
        return (List<PostResponseDto>) listMapperPostToDto.mapList(postRepo.findPostsByTitle(titleKeyWord), new PostResponseDto());
    }
}
