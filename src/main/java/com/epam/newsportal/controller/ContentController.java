package com.epam.newsportal.controller;


import com.epam.newsportal.domain.Comment;
import com.epam.newsportal.domain.Content;
import com.epam.newsportal.domain.User;
import com.epam.newsportal.repos.CommentRepository;
import com.epam.newsportal.repos.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("content")
public class ContentController {
    @Autowired
    private ContentRepository contentRepository;

    @Value("${upload.path}")
    private String uploadPath;

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

    @GetMapping("news")
    public String show(Map<String, Object> model){
        List<Content> news = contentRepository.findAll();
        model.put("news", news);
        return "news";
    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable("id") Long id){
        contentRepository.deleteById(id);
        return "index";
    }


    @PostMapping("create")
    public String create(@AuthenticationPrincipal User user, @RequestParam String title,
                         @RequestParam("file") MultipartFile file,
                         @RequestParam String content, Map<String, Object> model) throws IOException {
        Content contentNew = new Content(user, title, content, new Date());

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }


            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            contentNew.setFilename(resultFilename);
        }
        contentRepository.save(contentNew);
        List<Content> contents = contentRepository.findAll();
        model.put("news", contents);
        return "news";
    }

    @PostMapping("edit/{id}")
    public String edit(@PathVariable("id") Long id, @RequestParam String newContent){
        Content content = contentRepository.findById(id).get();
        content.setContent(newContent);
        contentRepository.save(content);
        return "index";
    }


}
