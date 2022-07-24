package com.epam.newsportal.repos;

import com.epam.newsportal.domain.UploadedFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<UploadedFile, Long> {
    public UploadedFile findByContentId(Long id);

}
