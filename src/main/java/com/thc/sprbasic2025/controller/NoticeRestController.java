package com.thc.sprbasic2025.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/api/notice")
@RestController
public class NoticeRestController {

    @GetMapping("/create")
    public int create(@RequestParam String title, String content, @RequestParam String author){
        return 1;
    }

}
