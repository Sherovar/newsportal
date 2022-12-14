package com.epam.newsportal.controller;

import com.epam.newsportal.dto.ContentDto;
import com.epam.newsportal.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {
    @Autowired
    ContentService contentService;

    @GetMapping("/")
    public String show(Map<String, Object> model) {
        List<ContentDto> news = contentService.getContentList();
        model.put("news", news);
        return "index";
    }
}
