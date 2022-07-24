package com.epam.newsportal.domain;

import javax.persistence.*;

@Entity
@Table(name = "file")
public class UploadedFile {
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    private String filename;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Content content;

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
