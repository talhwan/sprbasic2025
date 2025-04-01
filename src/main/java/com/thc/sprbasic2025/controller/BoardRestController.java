package com.thc.sprbasic2025.controller;

import com.thc.sprbasic2025.domain.Board;
import com.thc.sprbasic2025.service.BoardService;
import org.springframework.web.bind.annotation.*;

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
    public Long create(@RequestBody Map<String, Object> param){
        System.out.println("title : " + param.get("title"));
        return boardService.create(param);
    }

    @PutMapping("")
    public void update(@RequestBody Map<String, Object> param){
        boardService.update(param);
    }
    @DeleteMapping("")
    public void delete(@RequestBody Map<String, Object> param){
        Long id = Long.parseLong(param.get("id") + "");
        boardService.delete(id);
    }

    @GetMapping("/list")
    public List<Board> list(){
        return boardService.list();
    }
    @GetMapping("/detail")
    public Board detail(@RequestParam Long id){
        return boardService.detail(id);
    }

}
