package com.epam.newsportal.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.response.SecurityMockMvcResultMatchers.authenticated;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("/application-test.properties")
@Sql(value = {"/create-user-before.sql", "/content-before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/content-after.sql", "/create-user-after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@WithUserDetails(value = "test")

public class ContentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void userNameTest() throws Exception {
        ResultMatcher test = xpath("//div[@id='navbarSupportedContent']/div").string("test");
        this.mockMvc.perform(get("/content/news"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//div[@id='navbarSupportedContent']/div").string("test"));
    }

    @Test
    public void contentListTest() throws Exception {
        this.mockMvc.perform(get("/content/news"))
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//div[@id='content-list']/div").nodeCount(4));
    }

    @Test
    public void addContentTest() throws Exception{
        MockHttpServletRequestBuilder multipart = multipart("/content/create")
                .file("file", "test file".getBytes())
                .param("content", "test content")
                .param("title", "test title")
                .with(csrf());

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(authenticated())
                .andExpect(xpath("//div[@id='content-list']/div").nodeCount(5))
                .andExpect(xpath("//div[@id='content-list']/div[@data-id='10']").exists())
                .andExpect(xpath("//div[@id='content-list']/div[@data-id='10']/div/a").string("test title"))
                .andExpect(xpath("//div[@id='content-list']/div[@data-id='10']/div/span").string("test content"));
    }



}