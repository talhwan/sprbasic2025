package com.thc.sprbasic2025.service;

import com.thc.sprbasic2025.domain.Board;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.List;

@Service
public interface BoardService {
    Long create(Map<String, Object> param);
    List<Board> list();
    Board detail(Long id);
}
