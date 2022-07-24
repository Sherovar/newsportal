package com.epam.newsportal.service;

import com.epam.newsportal.domain.Comment;
import com.epam.newsportal.repos.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    public List<Comment> getByContentId(Long id){
        return commentRepository.getAllByContentId(id);
    }

    public void deleteById (Long id){
        commentRepository.deleteById(id);
    }

    public void saveComment(Comment comment){
        commentRepository.save(comment);
    }

    public Comment getById(Long id){
        return commentRepository.findById(id).get();
    }

    public void editComment(Comment comment){}
}
