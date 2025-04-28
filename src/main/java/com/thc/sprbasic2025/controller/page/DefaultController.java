package com.thc.sprbasic2025.controller.page;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.URLConnection;
import java.net.URLEncoder;
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
    @RequestMapping("/{page}")
    public String page(@PathVariable String page){
        return page;
    }

    @ResponseBody
    @RequestMapping(value = "/image/{file_name:.+}", method = {RequestMethod.GET,RequestMethod.POST})
    public byte[] getImage(@PathVariable("file_name") String file_name, HttpServletRequest request) throws Exception {
        //logger.info("file_name : " + file_name);
        //logger.info("root_path : " + root_path);
        byte[] return_byte = null;
        //해당 이미지를 byte[]형태로 반환
        File file = new File("C:/workspace/uploadfiles/sprbasic2025/" + file_name);
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            return_byte = IOUtils.toByteArray(in);
        } catch (FileNotFoundException e) {
            //logger.info("FileNotFoundException / file_name : " + file_name);
            //e.printStackTrace();
        } catch (IOException e) {
            //logger.info("IOException / file_name : " + file_name);
            //e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    //logger.info("final Exception / file_name : " + file_name);
                }
            }
        }
        return return_byte;
    }
    @ResponseBody
    @RequestMapping(value = "/download/{file_name:.+}", method = RequestMethod.GET)
    public void download(@PathVariable("file_name") String file_name, @RequestParam Map<String, Object> map, HttpServletRequest request, HttpServletResponse response) throws IOException {
        //logger.info("root_path : " + root_path);
        File file = new File("C:/workspace/uploadfiles/sprbasic2025/" + file_name);

        //여기는 response 에 설정해주는 부분인데, 어려우면 당분간은 패쓰!!
        String mimeType= URLConnection.guessContentTypeFromName(file_name);		//--- 파일의 mime타입을 확인합니다.
        if(mimeType==null){			//--- 마임타입이 없을 경우 application/octet-stream으로 설정합니다.
            mimeType = "application/octet-stream";
        }
        response.setContentType(mimeType);	//--- response에 mimetype을 설정합니다.
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment; filename=\""+ URLEncoder.encode(file.getName(), "utf-8") +"\"");
        //

        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));	//--- inputstream 객체를 얻습니다.
        FileCopyUtils.copy(inputStream, response.getOutputStream());		//--- inputstream으로 파일을 읽고 outputsream으로 파일을 씁니다.
    }

    /*@RequestMapping("/sum")
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
    }*/

}
