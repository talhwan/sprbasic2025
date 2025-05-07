package com.thc.sprbasic2025.service;

import com.thc.sprbasic2025.dto.DefaultDto;
import com.thc.sprbasic2025.dto.BoardimgDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BoardimgService {
    /**/
    DefaultDto.CreateResDto create(BoardimgDto.CreateReqDto param);
    void update(BoardimgDto.UpdateReqDto param);
    void delete(DefaultDto.DeleteReqDto param);
    BoardimgDto.DetailResDto detail(DefaultDto.DetailReqDto param);
    List<BoardimgDto.DetailResDto> list(BoardimgDto.ListReqDto param);
    DefaultDto.PagedListResDto pagedList(BoardimgDto.PagedListReqDto param);
    List<BoardimgDto.DetailResDto> scrollList(BoardimgDto.ScrollListReqDto param);
}
