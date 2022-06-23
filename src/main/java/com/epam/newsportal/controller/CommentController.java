package com.epam.newsportal.controller;

import com.epam.newsportal.domain.Comment;
import com.epam.newsportal.domain.Content;
import com.epam.newsportal.domain.User;
import com.epam.newsportal.repos.CommentRepository;
import com.epam.newsportal.repos.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private ContentRepository contentRepository;


    @PostMapping("deletecomment")
    public String delete(@RequestParam Long id) {
        commentRepository.deleteById(id);
        return "main";
    }


    @PostMapping("create/{id}")
    public String create(@AuthenticationPrincipal User user, @RequestParam String comment,
                         Map<String, Object> model,@PathVariable("id") Long newsId) {
        Comment commentNew = new Comment(comment, contentRepository.findById(newsId).get(), user, new Date());
        commentRepository.save(commentNew);
        List<Content> contents = new ArrayList<>();
        contents.add(contentRepository.findById(newsId).get());
        List<Comment> comments = commentRepository.findAllByContentId(newsId);
        model.put("comments", comments);
        model.put("news", contents);
        return "newspage";
    }

    @PostMapping("edit/{id}")
    public String edit(@PathVariable("id") Long id, String newComment, Map<String, Object> model) {
        Comment comment = commentRepository.findById(id).get();
        comment.setComment(newComment);
        commentRepository.save(comment);
        return "index";
    }
}
