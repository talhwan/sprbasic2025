package com.thc.sprbasic2025.service;

import com.thc.sprbasic2025.domain.Board;
import com.thc.sprbasic2025.dto.BoardDto;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.List;

@Service
public interface BoardService {
    BoardDto.CreateResDto create(BoardDto.CreateReqDto param);
    void update(BoardDto.UpdateReqDto param);
    void delete(BoardDto.DeleteReqDto param);
    List<BoardDto.DetailResDto> list();
    BoardDto.DetailResDto detail(BoardDto.DetailReqDto param);
}
