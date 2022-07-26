package com.epam.newsportal.dto;

import com.epam.newsportal.domain.Content;
import com.epam.newsportal.domain.User;
import com.epam.newsportal.utils.MappingUtils;

import java.util.Date;

public class CommentDto {
    private final MappingUtils mappingUtils = new MappingUtils();

    private long id;
    private String comment;
    private Date creationDate;
    private User user;
    private ContentDto contentDto;

    public CommentDto(){};

    public CommentDto(String comment,ContentDto contentDto , User user, Date creationDate) {
        this.comment = comment;
        this.creationDate = creationDate;
        this.user = user;
        this.contentDto = contentDto;
    }

    public String getUsername(){
        return user != null ? user.getUsername() : "none";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ContentDto getContent() {
        return contentDto;
    }

    public void setContent(ContentDto contentDto) {
        this.contentDto = contentDto;
    }
}
