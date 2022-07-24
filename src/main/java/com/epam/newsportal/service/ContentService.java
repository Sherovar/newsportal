package com.epam.newsportal.service;

import com.epam.newsportal.controller.ContentController;
import com.epam.newsportal.domain.Content;
import com.epam.newsportal.repos.ContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ContentService {
    @Autowired
    ContentRepository contentRepository;
    @Autowired
    FileService fileService;

    public void addContent(MultipartFile file, Content content) throws IOException {
        fileService.addFile(file, content);
    }

    public List<Content> getContentList(){
        return contentRepository.findAll();
    }

    public Content getContentById(Long id){
        return contentRepository.findById(id).get();
    }

    public void deleteContentById(Long id){
        contentRepository.deleteById(id);
    }

    public void saveContent(Content content){
        contentRepository.save(content);
    }

}
