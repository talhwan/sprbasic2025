package com.thc.sprbasic2025.controller;

import com.thc.sprbasic2025.dto.BoardlikeDto;
import com.thc.sprbasic2025.dto.DefaultDto;
import com.thc.sprbasic2025.service.BoardlikeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/boardlike")
@RestController
public class BoardlikeRestController {

    final BoardlikeService boardlikeService;
    public BoardlikeRestController(BoardlikeService boardlikeService){
        this.boardlikeService = boardlikeService;
    }

    @PostMapping("/toggle")
    public ResponseEntity<DefaultDto.CreateResDto> toggle(@RequestBody BoardlikeDto.CreateReqDto params){
        return ResponseEntity.ok(boardlikeService.toggle(params));
    }

    /**/

    @PostMapping("")
    public ResponseEntity<DefaultDto.CreateResDto> create(@RequestBody BoardlikeDto.CreateReqDto params){
        return ResponseEntity.ok(boardlikeService.create(params));
    }

    @PutMapping("")
    public ResponseEntity<Void> update(@RequestBody BoardlikeDto.UpdateReqDto params){
        boardlikeService.update(params);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    @DeleteMapping("")
    public ResponseEntity<Void> delete(@RequestBody DefaultDto.DeleteReqDto params){
        boardlikeService.delete(params);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/detail")
    public ResponseEntity<BoardlikeDto.DetailResDto> detail(DefaultDto.DetailReqDto params){
        return ResponseEntity.ok(boardlikeService.detail(params));
    }

    @GetMapping("/list")
    public ResponseEntity<List<BoardlikeDto.DetailResDto>> list(BoardlikeDto.ListReqDto params){
        return ResponseEntity.ok(boardlikeService.list(params));
    }

    @GetMapping("/pagedList")
    public ResponseEntity<DefaultDto.PagedListResDto> pagedList(BoardlikeDto.PagedListReqDto params){
        return ResponseEntity.ok(boardlikeService.pagedList(params));
    }
    @GetMapping("/scrollList")
    public ResponseEntity<List<BoardlikeDto.DetailResDto>> scrollList(BoardlikeDto.ScrollListReqDto params){
        return ResponseEntity.ok(boardlikeService.scrollList(params));
    }

}
