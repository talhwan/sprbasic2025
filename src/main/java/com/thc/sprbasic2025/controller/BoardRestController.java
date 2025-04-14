package com.thc.sprbasic2025.controller;

import com.thc.sprbasic2025.domain.Board;
import com.thc.sprbasic2025.dto.BoardDto;
import com.thc.sprbasic2025.dto.DefaultDto;
import com.thc.sprbasic2025.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/board")
@RestController
public class BoardRestController {

    final BoardService boardService;
    public BoardRestController(BoardService boardService){
        this.boardService = boardService;
    }

    @PostMapping("")
    public DefaultDto.CreateResDto create(@RequestBody BoardDto.CreateReqDto params){
        return boardService.create(params);
    }

    @PutMapping("")
    public void update(@RequestBody BoardDto.UpdateReqDto params){
        boardService.update(params);
    }
    @DeleteMapping("")
    public void delete(@RequestBody DefaultDto.DeleteReqDto params){
        boardService.delete(params);
    }

    @GetMapping("/list")
    public List<BoardDto.DetailResDto> list(BoardDto.ListReqDto params){
        return boardService.list(params);
    }
    @GetMapping("/detail")
    public BoardDto.DetailResDto detail(DefaultDto.DetailReqDto params){
        return boardService.detail(params);
    }


    @GetMapping("/pagedList")
    public DefaultDto.PagedListResDto pagedList(BoardDto.PagedListReqDto params){
        return boardService.pagedList(params);
    }

}
