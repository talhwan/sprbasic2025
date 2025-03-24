package com.thc.sprbasic2025.service.impl;

import com.thc.sprbasic2025.service.PostService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostServiceImpl implements PostService {

    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

    @Override
    public Map<String, Object> create(Map<String, Object> param){
        int order = list.size() + 1;
        Map<String, Object> board = new HashMap<String, Object>();
        board.put("order", order);
        board.put("title", param.get("title"));
        board.put("content", param.get("content"));

        list.add(board);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", 200);
        result.put("order", order);
        return result;
    }
    @Override
    public Map<String, Object> update(int order,Map<String, Object> param){
        Map<String, Object> board = list.get(order - 1);
        if(board.get("order") != null){
            if(param.get("title") != null){
                board.put("title", param.get("title"));
            }
            if(param.get("content") != null){
                board.put("content", param.get("content"));
            }
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", 200);
        return result;
    }
    @Override
    public Map<String, Object> delete(int order){
        Map<String, Object> board = list.get(order - 1);
        board.remove("order");
        board.remove("title");
        board.remove("content");

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", 200);
        return result;
    }
    @Override
    public Map<String, Object> detail(int order){
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("status", 200);
        result.put("data", list.get(order - 1));
        return result;
    }
    @Override
    public List<Map<String, Object>> list(){
        return list;
    }
}
