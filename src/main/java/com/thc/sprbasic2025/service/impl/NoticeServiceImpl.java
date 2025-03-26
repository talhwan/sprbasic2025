package com.thc.sprbasic2025.service.impl;

import com.thc.sprbasic2025.domain.Notice;
import com.thc.sprbasic2025.repository.NoticeRepository;
import com.thc.sprbasic2025.service.NoticeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NoticeServiceImpl implements NoticeService {

    final NoticeRepository noticeRepository;
    public NoticeServiceImpl(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
    int indexNumber = 1;

    @Override
    public int create(String title, String content, String author) {
        Map<String, Object> notice = new HashMap<String, Object>();
        notice.put("title", title);
        notice.put("content", content);
        notice.put("author", author);
        notice.put("id", indexNumber++);

        list.add(notice);

        Notice newNotice = new Notice();
        newNotice.setId(Long.valueOf(indexNumber));
        newNotice.setTitle(title);
        newNotice.setContent(content);
        newNotice.setAuthor(author);

        noticeRepository.save(newNotice);

        return 200;
    }

    @Override
    public void update(Map<String, Object> param) {

        System.out.println("param : " + param);

        int id = Integer.parseInt(param.get("id") + "");
        Map<String, Object> notice = detail(id);
        if(notice != null){
            String title = (String) param.get("title");
            if(title != null && !title.isEmpty()){
                notice.put("title", title);
            }
            String content = (String) param.get("content");
            if(content != null && !content.isEmpty()){
                notice.put("content", content);
            }
        }
    }

    @Override
    public void delete(int id) {
        Map<String, Object> notice = detail(id);
        list.remove(notice);
    }

    @Override
    public List<Map<String, Object>> list(String title, String author) {
        if((title == null || title.isEmpty()) && (author == null || author.isEmpty())){
            return list;
        }
        List<Map<String, Object>> returnList = new ArrayList<Map<String, Object>>();
        for(Map<String, Object> each : list){
            boolean needToAdd = false;
            String eachTitle = (String) each.get("title");
            String eachAuthor = (String) each.get("author");
            if(title != null && !title.isEmpty()){
                if(eachTitle.contains(title)){
                    needToAdd = true;
                    //returnList.add(each);
                }
            }
            if(author != null && !author.isEmpty()){
                if(eachAuthor.contains(author)){
                    needToAdd = true;
                    //returnList.add(each);
                }
            }
            if(needToAdd){
                returnList.add(each);
            }
        }
        return returnList;
    }

    @Override
    public Map<String, Object> detail(int id) {
        for(Map<String, Object> each : list){
            if(each != null){
                int eachId = Integer.parseInt(each.get("id") + "");
                if(eachId == id){
                    return each;
                }
            }
        }
        return null;
    }
}
