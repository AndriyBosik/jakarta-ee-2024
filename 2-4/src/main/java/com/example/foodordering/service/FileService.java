package com.example.foodordering.service;

import jakarta.servlet.http.Part;

import java.util.List;

public interface FileService {
    String upload(List<Part> parts, String filepath, String filename);
}
