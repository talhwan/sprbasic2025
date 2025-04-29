package com.thc.sprbasic2025.service.impl;

import com.thc.sprbasic2025.domain.User;
import com.thc.sprbasic2025.dto.UserDto;
import com.thc.sprbasic2025.dto.DefaultDto;
import com.thc.sprbasic2025.mapper.UserMapper;
import com.thc.sprbasic2025.repository.UserRepository;
import com.thc.sprbasic2025.service.UserService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceimpl implements UserService {

    final UserRepository userRepository;
    final UserMapper userMapper;
    public UserServiceimpl(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public DefaultDto.CreateResDto login(UserDto.LoginReqDto param) {
        User user = userRepository.findByUsernameAndPassword(param.getUsername(), param.getPassword());
        if(user == null){
            //throw new RuntimeException("id or password error!!");
            //로그인 실패
            return DefaultDto.CreateResDto.builder().id((long) -100).build();
        } else {
            //로그인 성공
            return DefaultDto.CreateResDto.builder().id(user.getId()).build();
        }
    }

    /**/

    @Override
    public DefaultDto.CreateResDto create(UserDto.CreateReqDto param) {
        User user = userRepository.findByUsername(param.getUsername());
        if(user != null){
            throw new RuntimeException("already exist");
        }
        return userRepository.save(param.toEntity()).toCreateResDto();
    }

    @Override
    public void update(UserDto.UpdateReqDto param) {
        User user = userRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        if(param.getDeleted() != null){ user.setDeleted(param.getDeleted()); }
        if(param.getPassword() != null){ user.setPassword(param.getPassword()); }
        if(param.getName() != null){ user.setName(param.getName()); }
        if(param.getNick() != null){ user.setNick(param.getNick()); }
        if(param.getPhone() != null){ user.setPhone(param.getPhone()); }
        userRepository.save(user);
    }

    @Override
    public void delete(DefaultDto.DeleteReqDto param) {
        update(UserDto.UpdateReqDto.builder().id(param.getId()).deleted(true).build());
    }

    @Override
    public UserDto.DetailResDto detail(DefaultDto.DetailReqDto param) {
        UserDto.DetailResDto user = userMapper.detail(param.getId());
        return user;
    }

    @Override
    public List<UserDto.DetailResDto> list(UserDto.ListReqDto param) {
        return detailList(userMapper.list(param));
    }
    public List<UserDto.DetailResDto> detailList(List<UserDto.DetailResDto> list){
        List<UserDto.DetailResDto> newList = new ArrayList<>();
        for(UserDto.DetailResDto each : list){
            newList.add(detail(DefaultDto.DetailReqDto.builder().id(each.getId()).build()));
        }
        return newList;
    }

    @Override
    public DefaultDto.PagedListResDto pagedList(UserDto.PagedListReqDto param) {
        DefaultDto.PagedListResDto res = param.init(userMapper.pagedListCount(param));
        res.setList(detailList(userMapper.pagedList(param)));
        return res;
    }

    @Override
    public List<UserDto.DetailResDto> scrollList(UserDto.ScrollListReqDto param) {
        param.init();

        //
        if("name".equals(param.getOrderby())){
            String mark = param.getMark();
            if(mark != null && !mark.isEmpty()){
                UserDto.DetailResDto user = userMapper.detail(Long.parseLong(mark));
                if(user != null){
                    mark = user.getName() + "_" + user.getId();
                    param.setMark(mark);
                }
            }
        }

        return detailList(userMapper.scrollList(param));
    }


}
