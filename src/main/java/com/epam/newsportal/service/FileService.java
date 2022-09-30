package com.epam.newsportal.service;

import com.epam.newsportal.domain.Content;
import com.epam.newsportal.domain.UploadedFile;
import com.epam.newsportal.repos.ContentRepository;
import com.epam.newsportal.repos.FileRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileService {

    @Value("${upload.path}")
    private String uploadPath;

    final
    FileRepository fileRepository;

    final
    ContentRepository contentRepository;


    public FileService(FileRepository fileRepository, ContentRepository contentRepository) {
        this.fileRepository = fileRepository;
        this.contentRepository = contentRepository;
    }

    public void addFile(MultipartFile file, Content content) throws IOException {
        UploadedFile newFile = new UploadedFile();
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }


            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            newFile.setContent(content);
            newFile.setFilename(resultFilename);
            fileRepository.save(newFile);
        }
    }

    public String getFilePath (Content content){
        return content.getUploadedFile().getFilename();
    }

    public void deleteFile(Content content){
        File file = new File(uploadPath + "/" + content.getFilename());
        file.delete();
    }

}
