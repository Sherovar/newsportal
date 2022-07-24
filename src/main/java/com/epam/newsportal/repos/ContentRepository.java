package com.epam.newsportal.repos;

import com.epam.newsportal.domain.Content;
import com.epam.newsportal.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content, Long> {
    void deleteById (Long id);
}
