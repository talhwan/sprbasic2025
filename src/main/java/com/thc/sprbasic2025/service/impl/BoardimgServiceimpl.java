package com.thc.sprbasic2025.service.impl;

import com.thc.sprbasic2025.domain.Boardimg;
import com.thc.sprbasic2025.dto.DefaultDto;
import com.thc.sprbasic2025.dto.BoardimgDto;
import com.thc.sprbasic2025.mapper.BoardimgMapper;
import com.thc.sprbasic2025.repository.BoardimgRepository;
import com.thc.sprbasic2025.service.BoardimgService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardimgServiceimpl implements BoardimgService {

    final BoardimgRepository userRepository;
    final BoardimgMapper userMapper;
    public BoardimgServiceimpl(BoardimgRepository userRepository, BoardimgMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    /**/

    @Override
    public DefaultDto.CreateResDto create(BoardimgDto.CreateReqDto param) {
        return userRepository.save(param.toEntity()).toCreateResDto();
    }

    @Override
    public void update(BoardimgDto.UpdateReqDto param) {
        Boardimg user = userRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        if(param.getDeleted() != null){ user.setDeleted(param.getDeleted()); }
        userRepository.save(user);
    }

    @Override
    public void delete(DefaultDto.DeleteReqDto param) {
        update(BoardimgDto.UpdateReqDto.builder().id(param.getId()).deleted(true).build());
    }

    @Override
    public BoardimgDto.DetailResDto detail(DefaultDto.DetailReqDto param) {
        BoardimgDto.DetailResDto user = userMapper.detail(param.getId());
        return user;
    }

    @Override
    public List<BoardimgDto.DetailResDto> list(BoardimgDto.ListReqDto param) {
        return detailList(userMapper.list(param));
    }
    public List<BoardimgDto.DetailResDto> detailList(List<BoardimgDto.DetailResDto> list){
        List<BoardimgDto.DetailResDto> newList = new ArrayList<>();
        for(BoardimgDto.DetailResDto each : list){
            newList.add(detail(DefaultDto.DetailReqDto.builder().id(each.getId()).build()));
        }
        return newList;
    }

    @Override
    public DefaultDto.PagedListResDto pagedList(BoardimgDto.PagedListReqDto param) {
        DefaultDto.PagedListResDto res = param.init(userMapper.pagedListCount(param));
        res.setList(detailList(userMapper.pagedList(param)));
        return res;
    }

    @Override
    public List<BoardimgDto.DetailResDto> scrollList(BoardimgDto.ScrollListReqDto param) {
        param.init();
        return detailList(userMapper.scrollList(param));
    }


}
