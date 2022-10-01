package com.epam.newsportal.dto;

import com.epam.newsportal.domain.Content;
import com.epam.newsportal.domain.User;
import com.epam.newsportal.utils.MappingUtils;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class CommentDto {
    private long id;

    @NotBlank(message = "Please fill comment")
    @Length(max = 255, message = "comment too long")
    private String comment;

    private Date creationDate;
    private User user;
    private ContentDto contentDto;

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
