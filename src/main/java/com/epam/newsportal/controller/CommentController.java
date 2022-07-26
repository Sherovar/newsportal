package com.epam.newsportal.controller;

import com.epam.newsportal.domain.User;
import com.epam.newsportal.dto.CommentDto;
import com.epam.newsportal.dto.ContentDto;
import com.epam.newsportal.service.CommentService;
import com.epam.newsportal.service.ContentService;
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
    CommentService commentService;
    @Autowired
    ContentService contentService;


    @PostMapping("delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        commentService.deleteById(id);
        return "index";
    }


    @PostMapping("create/{id}")
    public String create(@AuthenticationPrincipal User user, @RequestParam String comment,
                         Map<String, Object> model,@PathVariable("id") Long newsId) {
        CommentDto commentNew = new CommentDto(comment, contentService.getContentById(newsId), user, new Date());
        commentService.saveComment(commentNew);
        List<ContentDto> contents = new ArrayList<>();
        contents.add(contentService.getContentById(newsId));
        model.put("comments", commentService.getByContentId(newsId));
        model.put("news", contents);
        return "newspage";
    }

    @PostMapping("edit/{id}")
    public String edit(@PathVariable("id") Long id, String newComment) {
        CommentDto commentDto = commentService.getById(id);
        commentDto.setComment(newComment);
        commentService.saveComment(commentDto);
        return "index";
    }
}
