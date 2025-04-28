package com.thc.sprbasic2025.controller;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/api/default") //모든 메서드에 공통적으로 앞에 붙는 주소값을 넣어줄 수 있습니다!!
@RestController
public class DefaultRestController {

    @PostMapping("/uploadFile")
    public String uploadFile(MultipartFile file) throws IOException {
        System.out.println(file.getOriginalFilename());
        String fileName = file.getOriginalFilename();


        String tempPath = "C:/workspace/uploadfiles/sprbasic2025/";
        File newfile = new File(tempPath);
        // File 객체에 담긴 폴더가 존재하는지 물어봄!
        if(!newfile.exists()) {
            // File 객체에 담긴 폴더가 존재안하면 강제 생성!!
            newfile.mkdirs();
        }

        Date date = new Date();
        String temp_date = date.getTime() + "";
        FileCopyUtils.copy(file.getBytes(), new File(tempPath + temp_date + "_" + fileName));

        return temp_date + "_" + fileName;
    }

    /*@GetMapping("/test")
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
    }*/
}
