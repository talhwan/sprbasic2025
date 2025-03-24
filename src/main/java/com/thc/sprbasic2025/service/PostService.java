package com.thc.sprbasic2025.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface PostService {
    Map<String, Object> create(Map<String, Object> param);
    Map<String, Object> update(int order, Map<String, Object> param);
    Map<String, Object> delete(int order);
    Map<String, Object> detail(int order);
    List<Map<String, Object>> list();
}
