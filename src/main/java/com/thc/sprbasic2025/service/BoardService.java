package com.thc.sprbasic2025.service;

import com.thc.sprbasic2025.domain.Board;
import com.thc.sprbasic2025.dto.BoardDto;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.List;

@Service
public interface BoardService {
    BoardDto.CreateResDto create(BoardDto.CreateReqDto param);
    void update(Map<String, Object> param);
    void delete(Long id);
    List<Board> list();
    Board detail(Long id);
}
