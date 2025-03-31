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

    @GetMapping("/create")
    public Long create(@RequestParam Map<String, Object> param){
        System.out.println("title : " + param.get("title"));
        return boardService.create(param);
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
