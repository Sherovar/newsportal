package com.epam.newsportal.service;

import com.epam.newsportal.domain.Content;
import com.epam.newsportal.dto.ContentDto;
import com.epam.newsportal.repos.ContentRepository;
import com.epam.newsportal.utils.MappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
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

    @Transactional
    public void addContent(MultipartFile file, Content content) {
        contentRepository.save(content);
        fileService.addFile(file, content);
    }
    public void addContent(Content content){
        contentRepository.save(content);

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
