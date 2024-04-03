package com.example.foodordering.service;

import jakarta.servlet.http.Part;

import java.io.File;
import java.util.List;

public interface FileService {
    String upload(List<Part> parts, String filepath, String filename);

    String upload(String filepath, File file);
}
