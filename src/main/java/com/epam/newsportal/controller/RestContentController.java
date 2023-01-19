package com.epam.newsportal.controller;


import com.epam.newsportal.domain.Content;
import com.epam.newsportal.domain.User;
import com.epam.newsportal.dto.ContentDto;
import com.epam.newsportal.dto.RestContentDto;
import com.epam.newsportal.service.ContentService;
import com.epam.newsportal.utils.MappingUtils;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class RestContentController {
    @Autowired
    private ContentService contentService;

    @GetMapping("/{id}")
    public ContentDto getByIdContent(@PathVariable Long id) {
        return contentService.getContentById(id);
    }

    @GetMapping
    public List<ContentDto> getAllContent() {
        return contentService.getContentList();
    }

    @PutMapping("/{id}")
    public void updateContent(@PathVariable Long id, @RequestBody RestContentDto contentDto) {
        ContentDto contentById = contentService.getContentById(id);
        contentById.setContent(contentDto.getContent());
        contentById.setCreationDate(new Date());
        contentById.setTitle(contentDto.getTitle());
        contentService.saveContent(contentById);
    }

    @PostMapping
    public void createContent(@ApiIgnore @AuthenticationPrincipal User user,
                               @RequestBody RestContentDto contentDto) {
        Content content = new Content();
        content.setContent(contentDto.getContent());
        content.setTitle(contentDto.getContent());
        content.setUser(user);
        content.setCreationDate(new Date());
        contentService.addContent(content);
    }

    @DeleteMapping("{id}")
    public void deleteContent(@PathVariable Long id){
        contentService.deleteContentById(id);
    }
}
