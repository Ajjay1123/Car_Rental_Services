package com.carrental.app.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

@Service
public class FileStorageService {
    private final Path rootPath = Paths.get("uploads");

    public String store(MultipartFile file, String folder) {
        if (file == null || file.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "All documents are required");
        }
        try {
            Path targetFolder = rootPath.resolve(folder);
            Files.createDirectories(targetFolder);
            String originalName = file.getOriginalFilename() == null ? "document" : file.getOriginalFilename();
            String fileName = UUID.randomUUID().toString() + "_" + originalName.replaceAll("[^a-zA-Z0-9._-]", "_");
            Path targetFile = targetFolder.resolve(fileName);
            Files.copy(file.getInputStream(), targetFile, StandardCopyOption.REPLACE_EXISTING);
            return "/uploads/" + folder + "/" + fileName;
        } catch (IOException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to store file");
        }
    }
}
