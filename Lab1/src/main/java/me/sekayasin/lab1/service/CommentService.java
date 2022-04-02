package me.sekayasin.lab1.service;

import me.sekayasin.lab1.domain.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> findAll();
}
