package com.thc.sprbasic2025.service;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.List;

@Service
public interface NoticeService {
    int create(String title, String content, String author);
    void update(Map<String, Object> param);
    void delete(int id);
    List<Map<String, Object>> list(String title, String author);
    Map<String, Object> detail(int id);
}
