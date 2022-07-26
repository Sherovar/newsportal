package com.epam.newsportal.utils;

import com.epam.newsportal.domain.Comment;
import com.epam.newsportal.domain.Content;
import com.epam.newsportal.dto.CommentDto;
import com.epam.newsportal.dto.ContentDto;
import org.springframework.stereotype.Component;

@Component
public class MappingUtils {

    public CommentDto mapToCommentDto(Comment comment){
        CommentDto commentDto = new CommentDto();
        commentDto.setComment(comment.getComment());
        commentDto.setContent(mapToContentDto(comment.getContent()));
        commentDto.setCreationDate(comment.getCreationDate());
        commentDto.setId(comment.getId());
        commentDto.setUser(comment.getUser());
        return commentDto;
    }

    public Comment mapToComment(CommentDto commentDto){
        Comment comment = new Comment();
        comment.setComment(commentDto.getComment());
        comment.setUser(commentDto.getUser());
        comment.setContent(mapToContent(commentDto.getContent()));
        comment.setId(commentDto.getId());
        return comment;
    }

    public ContentDto mapToContentDto(Content content){
        ContentDto contentDto = new ContentDto();
        contentDto.setContent(content.getContent());
        contentDto.setUploadedFile(content.getUploadedFile());
        contentDto.setCreationDate(content.getCreationDate());
        contentDto.setTitle(content.getTitle());
        contentDto.setUser(content.getUser());
        contentDto.setId(content.getId());
        return contentDto;
    }

    public Content mapToContent(ContentDto contentDto){
        Content content = new Content();
        content.setContent(contentDto.getContent());
        content.setUploadedFile(contentDto.getUploadedFile());
        content.setTitle(contentDto.getTitle());
        content.setId(contentDto.getId());
        content.setCreationDate(contentDto.getCreationDate());
        content.setUser(contentDto.getUser());
        return content;
    }

}
