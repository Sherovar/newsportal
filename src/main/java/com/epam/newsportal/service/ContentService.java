package com.epam.newsportal.service;

import com.epam.newsportal.dto.ContentDto;
import com.epam.newsportal.repos.ContentRepository;
import com.epam.newsportal.utils.MappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContentService {
    @Autowired
    ContentRepository contentRepository;
    @Autowired
    FileService fileService;

    private final MappingUtils mappingUtils;

    public ContentService(MappingUtils mappingUtils) {
        this.mappingUtils = mappingUtils;
    }

    public void addContent(MultipartFile file, ContentDto contentDto) throws IOException {
        fileService.addFile(file, mappingUtils.mapToContent(contentDto));
    }

    public List<ContentDto> getContentList(){
        return contentRepository.findAll().stream()
                .map(mappingUtils::mapToContentDto)
                .collect(Collectors.toList());
    }

    public ContentDto getContentById(Long id){
        return mappingUtils.mapToContentDto(contentRepository.findById(id).get());
    }

    public void deleteContentById(Long id){
        fileService.deleteFile(contentRepository.findById(id).get());
        contentRepository.deleteById(id);
    }

    public void saveContent(ContentDto content){
        contentRepository.save(mappingUtils.mapToContent(content));
    }

}
