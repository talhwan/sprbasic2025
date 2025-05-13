package com.thc.sprbasic2025.controller;

import com.thc.sprbasic2025.dto.BoardimgDto;
import com.thc.sprbasic2025.dto.DefaultDto;
import com.thc.sprbasic2025.service.BoardimgService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/boardimg")
@RestController
public class BoardimgRestController {

    final BoardimgService boardimgService;
    public BoardimgRestController(BoardimgService boardimgService){
        this.boardimgService = boardimgService;
    }

    @PostMapping("")
    public ResponseEntity<DefaultDto.CreateResDto> create(@RequestBody BoardimgDto.CreateReqDto params){
        return ResponseEntity.ok(boardimgService.create(params));
    }

    @PutMapping("")
    public ResponseEntity<Void> update(@RequestBody BoardimgDto.UpdateReqDto params){
        boardimgService.update(params);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestBody DefaultDto.DeleteReqDto params){
        boardimgService.delete(params);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/detail")
    public ResponseEntity<BoardimgDto.DetailResDto> detail(DefaultDto.DetailReqDto params){
        return ResponseEntity.ok(boardimgService.detail(params));
    }

    @GetMapping("/list")
    public ResponseEntity<List<BoardimgDto.DetailResDto>> list(BoardimgDto.ListReqDto params){
        return ResponseEntity.ok(boardimgService.list(params));
    }

    @GetMapping("/pagedList")
    public ResponseEntity<DefaultDto.PagedListResDto> pagedList(BoardimgDto.PagedListReqDto params){
        return ResponseEntity.ok(boardimgService.pagedList(params));
    }
    @GetMapping("/scrollList")
    public ResponseEntity<List<BoardimgDto.DetailResDto>> scrollList(BoardimgDto.ScrollListReqDto params){
        return ResponseEntity.ok(boardimgService.scrollList(params));
    }

}
