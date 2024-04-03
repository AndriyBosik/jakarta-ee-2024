package com.example.foodordering.service.impl;

import com.example.foodordering.metadata.Const;
import com.example.foodordering.service.FileService;
import jakarta.ejb.Stateless;
import jakarta.servlet.http.Part;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Stateless
public class FileServiceImpl implements FileService {
    @Override
    public String upload(List<Part> parts, String filepath, String filename) {
        new File(filepath).mkdirs();
        String fullFilepath = filepath + filename;
        parts.forEach(part -> write(part, fullFilepath));
        return fullFilepath;
    }

    @Override
    public String upload(String filepath, File file) {
        String filename = UUID.randomUUID().toString();
        String extension = getExtension(getContentType(file));
        String relativePath = String.format("%s/%s.%s", Const.IMAGE_SHORT_PATH, filename, extension);
        if (StringUtils.endsWithAny(filepath, "/", "\\")) {
            filepath = filepath.substring(0, filepath.length() - 1);
        }
        String fullPath = String.format("%s%s", filepath, relativePath);
        try {
            Files.copy(file.toPath(), Paths.get(fullPath));
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
        return relativePath;
    }

    private String getExtension(String contentType) {
        String[] parts = contentType.split("/");
        return parts[parts.length - 1];
    }

    private String getContentType(File file) {
        try {
            return file.toURI().toURL().openConnection().getContentType();
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }

    private void write(Part part, String filepath) {
        try {
            part.write(filepath);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
