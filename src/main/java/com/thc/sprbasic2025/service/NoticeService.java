package com.thc.sprbasic2025.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.List;

@Service
public interface NoticeService {
    int create(String title, String content, String author);
    List<Map<String, Object>> list(String title, String author);
}
