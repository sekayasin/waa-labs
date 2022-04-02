package me.sekayasin.lab1.service.impl;

import me.sekayasin.lab1.domain.Comment;
import me.sekayasin.lab1.repo.CommentRepo;
import me.sekayasin.lab1.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepo commentRepo;

    @Autowired
    public CommentServiceImpl(CommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    @Override
    public List<Comment> findAll() {
        return commentRepo.findAll();
    }
}
