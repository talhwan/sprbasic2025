package com.thc.sprbasic2025.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api") //모든 메서드에 공통적으로 앞에 붙는 주소값을 넣어줄 수 있습니다!!
@RestController
public class DefaultRestController {

    @GetMapping("/test")
    public Map<String, Object> test(){
        Map<String, Object> map = new HashMap<>();
        map.put("status", 200);
        map.put("data", "hahaha");
        return map;
    }
    @GetMapping("/multiple")
    public Map<String, Object> multiple(int a, int b){
        System.out.println("a : " + a + ", b : " + b);
        int result = a * b;

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", 200);
        resultMap.put("result", result);
        return resultMap;
    }
    @GetMapping("/login")
    public Map<String, Object> multiple(@RequestParam String id, @RequestParam String pw){
        System.out.println("id : " + id + ", pw : " + pw);
        int resultCode = 0;
        if("1234".equals(id) && "1111".equals(pw)){
            resultCode = 200;
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("status", resultCode);
        return resultMap;
    }
}
