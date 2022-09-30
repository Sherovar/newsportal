package com.epam.newsportal.service;

import com.epam.newsportal.domain.Comment;
import com.epam.newsportal.dto.CommentDto;
import com.epam.newsportal.repos.CommentRepository;
import com.epam.newsportal.utils.MappingUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    private final MappingUtils mappingUtils = new MappingUtils();

    public List<CommentDto> getByContentId(Long id){
        return commentRepository.getAllByContentId(id).stream()
                .map(mappingUtils::mapToCommentDto)
                .collect(Collectors.toList());
    }

    public void deleteById (Long id){
        commentRepository.deleteById(id);
    }

    public void saveComment(Comment comment){
        commentRepository.save(comment);
    }
    public void saveComment(CommentDto commentDto){
        commentRepository.save(mappingUtils.mapToComment(commentDto));
    }

    public CommentDto getById(Long id){
        return mappingUtils.mapToCommentDto(commentRepository.findById(id).get());
    }

    public void editComment(Comment comment){}
}
