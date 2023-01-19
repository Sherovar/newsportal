package com.epam.newsportal.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RestContentDto {

    @NotBlank
    @Size(max = 2048, message = "content too long")
    public String content;

    @NotBlank
    @Size(max = 255, message = "title too long")
    private String title;

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
}
