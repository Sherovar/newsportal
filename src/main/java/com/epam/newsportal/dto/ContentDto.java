package com.epam.newsportal.dto;

import com.epam.newsportal.domain.UploadedFile;
import com.epam.newsportal.domain.User;

import java.util.Date;

public class ContentDto {
    Long id;
    String content;
    String title;
    UploadedFile uploadedFile;
    User user;
    Date creationDate;

    public ContentDto(){}

    public ContentDto(User user, String title, String content, Date creationDate) {
        this.title = title;
        this.user = user;
        this.content = content;
        this.creationDate = creationDate;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public String getFilename() {
        if (uploadedFile!=null)
            return uploadedFile.getFilename();
        return null;
    }
    public String getUsername(){
        return user != null ? user.getUsername() : "none";
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
