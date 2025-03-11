package com.thc.sprbasic2025.controller.page;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DefaultController {

    @RequestMapping({"/index", "", "/"}) // @RequestMapping 의 주소값은 스트링 한개도 가능, 스트링 배열도 가능!
    public String index(){
        return "index";
    }

    @RequestMapping("/sum")
    public String sum(@RequestParam int a, @RequestParam int b, String name, Model model){
        //@RequestParam 어노테이션이 붙은 파라미터인 경우에는 없으면 에러납니다..
        System.out.println("a : " + a);
        System.out.println("b : " + b);
        int sum = a + b;
        System.out.println("sum : " + sum);
        System.out.println("name : " + name);
        model.addAttribute("sum", sum);
        model.addAttribute("name", "!!" +  name);
        return "sum";
    }
    @RequestMapping("/test")
    public String test(){
        int a = 0;
        Integer a1 = 0;
        double b = 0.0;
        Double b1 = 0.0;
        boolean c = true;
        Boolean c1 = false;
        String d = "";

        a++;
        System.out.println("a : " + a++); //1
        System.out.println("a : " + ++a); //3

        //반복문
        int sum = 0;
        for(int i=0;i<10;i++){
            sum += i;
        }
        System.out.println("sum: " + sum);

        //sum1
        //0부터 10까지 더하는 while 문 작성하기!!
        sum = 0;
        int i1 = 0;
        while(i1<10){
            sum += i1;
            i1++;
        }
        System.out.println("sum: " + sum);

        if(sum > 50){
            c = false;
        } else if(sum > 10){
            c = true;
        } else {
            c = true;
        }

        switch (d){
            case "a":
            case "b":
                //
                break;
            default:
                //
                break;
        }

        Map<String, Object> map = new HashMap<>();
        map.put("11", "111");
        map.put("22", "222");
        map.remove("11");
        map.put("22", "3333");

        String aaaaa = (String) map.get("22");
        System.out.println("aaaaa : " + aaaaa);

        List<String> list = new ArrayList<>();
        list.add("adrgaergae");
        list.add("adrgaergae1");
        list.add("adrgaergae2");
        list.add("adrgaergae3");
        list.remove(2);

        for(int i=0;i<list.size();i++){
            System.out.println(list.get(i));
        }

        for(String s : list){
            System.out.println(s);
        }

        String[] array1 = {"a","b","c","d","e","f","g","h"};
        for(String s : array1){
            System.out.println(s);
        }

        return "sum";
    }

}
