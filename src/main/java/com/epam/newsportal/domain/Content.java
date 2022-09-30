package com.epam.newsportal.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.Date;


@Entity
@Table(name = "content")
public class Content {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "Please fill content")
    @Length(max = 2048, message = "content too long")
    private String content;

    @NotBlank(message = "Please fill title")
    @Length(max = 255, message = "title too long")
    private String title;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    private UploadedFile uploadedFile;


    private Date creationDate;

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    public String getUsername() {
        return user != null ? user.getUsername() : "none";
    }

    public String getFilename() {
        if (uploadedFile != null)
            return uploadedFile.getFilename();
        return null;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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



