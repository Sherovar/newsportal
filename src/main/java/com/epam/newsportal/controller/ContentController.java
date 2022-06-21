package com.epam.newsportal.controller;


import com.epam.newsportal.domain.Comment;
import com.epam.newsportal.domain.Content;
import com.epam.newsportal.domain.User;
import com.epam.newsportal.repos.CommentRepository;
import com.epam.newsportal.repos.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("content")
public class ContentController {
    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private CommentRepository commentRepository;

    @GetMapping("/{id}")
    public String showById(@PathVariable("id") Long id, Map<String , Object> model){
        List<Comment> comments = commentRepository.findAllByContentId(id);
        Content content = contentRepository.findById(id).get();
        List<Content> news = contentRepository.findAll();
        news.clear();
        news.add(content);
        model.put("news", news);
        model.put("comments", comments);
        return "newspage";
    }

//    @GetMapping("findauthor")
//    public List<Content> findByAuthor(@RequestParam String author){
//        List<Content> allByAuthor = contentRepository.findAllByAuthor(author);
//        return allByAuthor;
//    }

    @GetMapping("news")
    public String show(Map<String, Object> model){
        List<Content> news = contentRepository.findAll();
        model.put("news", news);
        return "news";
    }

    @DeleteMapping("delete")
    public void delete(@RequestParam Long id){
        contentRepository.deleteById(id);
    }


    @PostMapping("create")
    public String create(@AuthenticationPrincipal User user, @RequestParam String title,
                         @RequestParam String content, Map<String, Object> model){
        Content contentNew = new Content(user, title, content, new Date());
        contentRepository.save(contentNew);
        List<Content> contents = contentRepository.findAll();
        model.put("news", contents);
        return "news";
    }

    @PatchMapping("edit")
    public void edit(@RequestParam Long id, @RequestParam String newContent){
        Content content = contentRepository.findById(id).get();
        content.setContent(newContent);
        contentRepository.save(content);
    }


}
