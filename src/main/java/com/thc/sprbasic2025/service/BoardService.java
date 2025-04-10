package com.thc.sprbasic2025.service;

import com.thc.sprbasic2025.domain.Board;
import com.thc.sprbasic2025.dto.BoardDto;
import com.thc.sprbasic2025.dto.DefaultDto;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.List;

@Service
public interface BoardService {
    DefaultDto.CreateResDto create(BoardDto.CreateReqDto param);
    void update(BoardDto.UpdateReqDto param);
    void delete(DefaultDto.DeleteReqDto param);
    List<BoardDto.DetailResDto> list(BoardDto.ListReqDto param);
    BoardDto.DetailResDto detail(DefaultDto.DetailReqDto param);
}
