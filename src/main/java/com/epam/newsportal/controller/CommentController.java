package com.epam.newsportal.controller;

import com.epam.newsportal.domain.Comment;
import com.epam.newsportal.domain.User;
import com.epam.newsportal.dto.CommentDto;
import com.epam.newsportal.dto.ContentDto;
import com.epam.newsportal.service.CommentService;
import com.epam.newsportal.service.ContentService;
import com.epam.newsportal.utils.MappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("comment")
public class CommentController {

    @Autowired
    CommentService commentService;
    @Autowired
    ContentService contentService;
    @Autowired
    MappingUtils mappingUtils;


    @PostMapping("delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        commentService.deleteById(id);
        return "index";
    }


    @PostMapping("create/{id}")
    public String create(@AuthenticationPrincipal User user,
                         @Valid CommentDto commentDto,
                         Model model,
                         @PathVariable("id") Long newsId) {
        Comment comment = mappingUtils.mapToComment(commentDto, newsId);
        comment.setUser(user);
        comment.setCreationDate(new Date());
        commentService.saveComment(comment);
        List<ContentDto> contents = new ArrayList<>();
        contents.add(contentService.getContentById(newsId));
        model.addAttribute("comments", commentService.getByContentId(newsId));
        model.addAttribute("news", contents);
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
