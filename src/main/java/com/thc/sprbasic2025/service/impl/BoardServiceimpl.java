package com.thc.sprbasic2025.service.impl;

import com.thc.sprbasic2025.domain.Board;
import com.thc.sprbasic2025.dto.BoardDto;
import com.thc.sprbasic2025.dto.DefaultDto;
import com.thc.sprbasic2025.mapper.BoardMapper;
import com.thc.sprbasic2025.repository.BoardRepository;
import com.thc.sprbasic2025.service.BoardService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BoardServiceimpl implements BoardService {

    /*
    R => mapper (mybatis)
    C U D => Repository
    * */

    final BoardRepository boardRepository;
    final BoardMapper boardMapper;
    public BoardServiceimpl(BoardRepository boardRepository, BoardMapper boardMapper){
        this.boardRepository = boardRepository;
        this.boardMapper = boardMapper;
    }

    @Override
    public DefaultDto.CreateResDto create(BoardDto.CreateReqDto param) {
        return boardRepository.save(param.toEntity()).toCreateResDto();
    }

    @Override
    public void update(BoardDto.UpdateReqDto param) {
        Board board = boardRepository.findById(param.getId()).orElse(null);
        if(board == null){
            throw new RuntimeException("no data");
        }
        if(param.getDeleted() != null){ board.setDeleted(param.getDeleted()); }
        if(param.getTitle() != null){ board.setTitle(param.getTitle()); }
        if(param.getContent() != null){ board.setContent(param.getContent()); }
        if(param.getAuthor() != null){ board.setAuthor(param.getAuthor()); }
        boardRepository.save(board);
    }

    @Override
    public void delete(DefaultDto.DeleteReqDto param) {
        update(BoardDto.UpdateReqDto.builder().id(param.getId()).deleted(true).build());
    }

    @Override
    public BoardDto.DetailResDto detail(DefaultDto.DetailReqDto param) {
        /*
        Board board = boardRepository.findById(param.getId()).orElseThrow(() -> new RuntimeException(""));
        BoardDto.DetailResDto res = BoardDto.DetailResDto.builder()
                .id(param.getId())
                .deleted(board.getDeleted())
                .title(board.getTitle()).content(board.getContent()).author(board.getAuthor())
                .countread(board.getCountread())
                .build();
        return res;
        */
        BoardDto.DetailResDto board = boardMapper.detail(param.getId());
        return board;
    }

    @Override
    public List<BoardDto.DetailResDto> list(BoardDto.ListReqDto param) {
        return detailList(boardMapper.list(param));
    }
    public List<BoardDto.DetailResDto> detailList(List<BoardDto.DetailResDto> list){
        List<BoardDto.DetailResDto> newList = new ArrayList<>();
        for(BoardDto.DetailResDto each : list){
            newList.add(detail(DefaultDto.DetailReqDto.builder().id(each.getId()).build()));
        }
        return newList;
    }

    @Override
    public DefaultDto.PagedListResDto pagedList(BoardDto.PagedListReqDto param) {
        Integer perpage = param.getPerpage(); //한번에 볼 글 갯수
        if(perpage == null || perpage < 1){
            perpage = 10;
        }
        if(perpage > 100){
            perpage = 100;
        }
        param.setPerpage(perpage);

        int listsize = boardMapper.pagedListCount(param); // 총 글 갯수 가져오기!!
        int totalpage = listsize / perpage; // 101 / 10 = >10 ?? 11이 되어야 하는데?
        if(listsize % perpage > 0){
            totalpage++;
        }

        Integer callpage = param.getCallpage();
        if(callpage == null || callpage < 1){
            //페이지 수가 없거나, 1보다 작은 페이수를 호출할 때
            callpage = 1;
        }
        if(callpage > totalpage){
            //전체 페이지보다 더 다음 페이지를 호출할때!
            callpage = totalpage;
        }
        param.setCallpage(callpage);

        String orderby = param.getOrderby();
        if(orderby == null || orderby.isEmpty()){
            orderby = "id";
        }
        param.setOrderby(orderby);

        String orderway = param.getOrderway();
        if(orderway == null || orderway.isEmpty()){
            orderway = "DESC";
        }
        param.setOrderway(orderway);

        /*
        1페이지일때 -> 0
        2페이지일때 -> (2-1) * perpage
        * */
        int offset = (callpage - 1) * perpage;
        param.setOffset(offset);

        List<BoardDto.DetailResDto> list = boardMapper.pagedList(param);

        return DefaultDto.PagedListResDto.builder()
                .totalpage(totalpage)
                .callpage(callpage)
                .perpage(perpage)
                .listsize(listsize)
                .list(detailList(list))
                .build();
    }

    @Override
    public List<BoardDto.DetailResDto> scrollList(BoardDto.ScrollListReqDto param) {
        Integer perpage = param.getPerpage(); //한번에 볼 글 갯수
        if(perpage == null || perpage < 1){
            perpage = 10;
        }
        if(perpage > 100){
            perpage = 100;
        }
        param.setPerpage(perpage);

        String orderby = param.getOrderby();
        if(orderby == null || orderby.isEmpty()){
            orderby = "id";
        }
        param.setOrderby(orderby);
        if("id".equals(orderby)){

        } else if("title".equals(orderby)){
            String mark = param.getMark();
            if(mark != null && !mark.isEmpty()){
                BoardDto.DetailResDto board = boardMapper.detail(Long.parseLong(mark));
                if(board != null){
                    mark = board.getTitle() + "_" + board.getId();
                    param.setMark(mark);
                }
            }
        }

        String orderway = param.getOrderway();
        if(orderway == null || orderway.isEmpty()){
            orderway = "DESC";
        }
        param.setOrderway(orderway);

        List<BoardDto.DetailResDto> list = boardMapper.scrollList(param);
        return detailList(list);
    }


    }
