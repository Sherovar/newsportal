package com.epam.newsportal.controller;


import com.epam.newsportal.domain.Content;
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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("content")
public class ContentController {

    @Autowired
    ContentService contentService;

    @Autowired
    CommentService commentService;

    @Autowired
    MappingUtils mappingUtils;

    @GetMapping("/{id}")
    public String showById(@PathVariable("id") Long id, Map<String, Object> model) {
        List<CommentDto> comments = commentService.getByContentId(id);
        ContentDto content = contentService.getContentById(id);
        List<ContentDto> news = new ArrayList<>();
        news.add(content);
        model.put("news", news);
        model.put("comments", comments);
        return "newspage";
    }

    @GetMapping("news")
    public String show(Map<String, Object> model) {
        List<ContentDto> news = contentService.getContentList();
        model.put("news", news);
        return "news";
    }

    @PostMapping("delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        contentService.deleteContentById(id);
        return "index";
    }

    @PostMapping("create")
    public String create(@AuthenticationPrincipal User user,
                         @Valid ContentDto contentDto,
                         BindingResult bindingResult,
                         Model model,
                         @RequestParam("file") MultipartFile file) throws IOException {
        Content content = mappingUtils.mapToContent(contentDto);
        content.setUser(user);
        content.setCreationDate(new Date());
        if (bindingResult.hasErrors()){
            Map<String, String> errorsMap = ControllerUtil.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("content", content);
        } else {
            if (file != null && !file.getOriginalFilename().isEmpty()) {
                contentService.addContent(file, content);
            } else {
                contentService.addContent(content);
            }
            model.addAttribute("content", null);
        }
        List<ContentDto> contents = contentService.getContentList();
        model.addAttribute("news", contents);
        return "news";
    }



    @PostMapping("edit/{id}")
    public String edit(@PathVariable("id") Long id, @RequestParam String newContent) {
        ContentDto content = contentService.getContentById(id);
        content.setContent(newContent);
        contentService.saveContent(content);
        return "index";
    }


}
