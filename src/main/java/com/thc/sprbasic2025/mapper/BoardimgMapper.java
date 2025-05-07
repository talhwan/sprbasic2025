package com.thc.sprbasic2025.mapper;

import com.thc.sprbasic2025.dto.BoardimgDto;

import java.util.List;

public interface BoardimgMapper {
	BoardimgDto.DetailResDto detail(Long id);
	List<BoardimgDto.DetailResDto> list(BoardimgDto.ListReqDto param);

	List<BoardimgDto.DetailResDto> pagedList(BoardimgDto.PagedListReqDto param);
	int pagedListCount(BoardimgDto.PagedListReqDto param);
	List<BoardimgDto.DetailResDto> scrollList(BoardimgDto.ScrollListReqDto param);


}