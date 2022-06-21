package com.epam.newsportal.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")

public class Comment {
    public Comment() {
    }

    @Id
    @GeneratedValue
    private long id;
    private String comment;
    private Date creationDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Content content;

    public Comment(String comment,Content content , User user, Date creationDate) {
        this.comment = comment;
        this.creationDate = creationDate;
        this.user = user;
        this.content = content;
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

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }
}
