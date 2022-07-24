package com.epam.newsportal.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="content")
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

    private String filename;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private UploadedFile uploadedFile;

    private String content;
    private Date creationDate;

    public Content() {
    }

    public Content(String content, String title) {
        this.title = title;
        this.content = content;
    }

    public Content(User user, String title, String content, Date creationDate) {
        this.title = title;
        this.user = user;
        this.content = content;
        this.creationDate = creationDate;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public String getUsername(){
        return user != null ? user.getUsername() : "none";
    }

    public String getFilename() {
        if (uploadedFile!=null)
        return uploadedFile.getFilename();
        return null;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return user.getUsername();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + user.getUsername() + '\'' +
                ", content='" + content + '\'' +
                ", creationDate=" + creationDate +
                '}';
    }
}



