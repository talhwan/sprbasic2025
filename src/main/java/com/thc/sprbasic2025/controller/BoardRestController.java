package com.thc.sprbasic2025.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api/board")
@RestController
public class BoardRestController {

    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

    @GetMapping("/create")
    public Map<String, Object> create(@RequestParam String title, @RequestParam String content){
        System.out.println("title : " + title + ", content : " + content);
        int resultCode = 0;

        int order = list.size() + 1;
        Map<String, Object> board = new HashMap<>();
        board.put("order", order);
        board.put("title", title);
        board.put("content", content);

        list.add(board);

        System.out.println("list : " + list.size());
        resultCode = 200;

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", resultCode);
        resultMap.put("order", order);
        return resultMap;
    }
    @GetMapping("/list")
    public Map<String, Object> list(){
        int resultCode = 0;
        resultCode = 200;

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", resultCode);
        resultMap.put("data", list);
        return resultMap;
    }
    @GetMapping("/detail/{order}")
    public Map<String, Object> detail(@PathVariable("order") int order){
        int resultCode = 0;
        resultCode = 200;

        Map<String, Object> board = list.get(order - 1);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", resultCode);
        resultMap.put("data", board);
        return resultMap;
    }
}
