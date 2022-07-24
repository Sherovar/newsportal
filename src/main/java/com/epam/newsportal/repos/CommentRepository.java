package com.epam.newsportal.repos;

import com.epam.newsportal.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    void deleteById(Long id);
    List<Comment> getAllByContentId(Long id);
}
