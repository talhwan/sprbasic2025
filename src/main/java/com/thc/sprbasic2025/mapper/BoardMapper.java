package com.thc.sprbasic2025.mapper;

import com.thc.sprbasic2025.dto.BoardDto;

import java.util.List;

public interface BoardMapper {
	BoardDto.DetailResDto detail(Long id);
	List<BoardDto.DetailResDto> list(BoardDto.ListReqDto param);
}