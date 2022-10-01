package com.epam.newsportal.dto;

import com.epam.newsportal.domain.User;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

public class ContentDto {
    private Long id;

    @NotBlank(message = "Please fill content")
    @Size(max = 2048, message = "content too long")
    public String content;

    @NotBlank(message = "Please fill title")
    @Size(max = 255, message = "title too long")
    private String title;
    private Date creationDate;
    private String username;

    public void setUsername(String username){
        this.username = username;
    }

    public String getFilename() {
        return filename;
    }

    String filename;

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public ContentDto(){}


    public String getUsername(){
        return username;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
