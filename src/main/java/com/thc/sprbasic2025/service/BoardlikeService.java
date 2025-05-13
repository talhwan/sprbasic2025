package com.thc.sprbasic2025.service;

import com.thc.sprbasic2025.dto.BoardlikeDto;
import com.thc.sprbasic2025.dto.DefaultDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BoardlikeService {
    DefaultDto.CreateResDto toggle(BoardlikeDto.CreateReqDto param);
    /**/
    DefaultDto.CreateResDto create(BoardlikeDto.CreateReqDto param);
    void update(BoardlikeDto.UpdateReqDto param);
    void delete(DefaultDto.DeleteReqDto param);
    BoardlikeDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<BoardlikeDto.DetailResDto> list(BoardlikeDto.ListReqDto param);
    DefaultDto.PagedListResDto pagedList(BoardlikeDto.PagedListReqDto param);
    List<BoardlikeDto.DetailResDto> scrollList(BoardlikeDto.ScrollListReqDto param);
}
