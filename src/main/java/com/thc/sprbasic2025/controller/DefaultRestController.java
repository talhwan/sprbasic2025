package com.thc.sprbasic2025.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api") //모든 메서드에 공통적으로 앞에 붙는 주소값을 넣어줄 수 있습니다!!
@RestController
public class DefaultRestController {

    @GetMapping("/test")
    public Map<String, Object> test(int a, int b){
        System.out.println("a : " + a);
        Map<String, Object> map = new HashMap<>();
        map.put("status", 200);
        map.put("data", "hahaha");
        return map;
    }
    @GetMapping("/abc")
    public Map<String, Object> abc(){
        return null;
    }
}
