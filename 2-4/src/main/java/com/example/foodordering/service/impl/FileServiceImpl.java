package com.example.foodordering.service.impl;

import com.example.foodordering.service.FileService;
import jakarta.ejb.Singleton;
import jakarta.ejb.Stateless;
import jakarta.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Singleton
public class FileServiceImpl implements FileService {
    @Override
    public String upload(List<Part> parts, String filepath, String filename) {
        new File(filepath).mkdirs();
        String fullFilepath = filepath + filename;
        parts.forEach(part -> write(part, fullFilepath));
        return fullFilepath;
    }

    private void write(Part part, String filepath) {
        try {
            part.write(filepath);
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}
