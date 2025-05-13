package com.thc.sprbasic2025.service.impl;

import com.thc.sprbasic2025.domain.Board;
import com.thc.sprbasic2025.domain.Boardlike;
import com.thc.sprbasic2025.dto.BoardlikeDto;
import com.thc.sprbasic2025.dto.DefaultDto;
import com.thc.sprbasic2025.mapper.BoardlikeMapper;
import com.thc.sprbasic2025.repository.BoardRepository;
import com.thc.sprbasic2025.repository.BoardlikeRepository;
import com.thc.sprbasic2025.service.BoardlikeService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardlikeServiceimpl implements BoardlikeService {

    final BoardlikeRepository boardlikeRepository;
    final BoardlikeMapper boardlikeMapper;
    final BoardRepository boardRepository;
    public BoardlikeServiceimpl(
            BoardlikeRepository boardlikeRepository
            , BoardlikeMapper boardlikeMapper
            , BoardRepository boardRepository
    ){
        this.boardlikeRepository = boardlikeRepository;
        this.boardlikeMapper = boardlikeMapper;
        this.boardRepository = boardRepository;
    }


    @Override
    public DefaultDto.CreateResDto toggle(BoardlikeDto.CreateReqDto param) {
        Boardlike boardlike = boardlikeRepository.findByBoardIdAndUserId(param.getBoardId(), param.getUserId());
        System.out.println(boardlike);

        if(boardlike == null){
            //아직 좋아요 전!!
            create(param);
        } else {
            //좋아요 정보가 있긴 하네!!
            /*
            if(boardlike.getDeleted()){
                //이미 지워져 있네!!이제 다시 복원해볼까?
                update(BoardlikeDto.UpdateReqDto.builder().id(boardlike.getId()).deleted(false).build());
            } else {
                // 살아있는데.. 이제 지워야 하네!
                update(BoardlikeDto.UpdateReqDto.builder().id(boardlike.getId()).deleted(true).build());
            }
            */
            update(BoardlikeDto.UpdateReqDto.builder().id(boardlike.getId()).deleted(!boardlike.getDeleted()).build());
        }
        int countlike = boardlikeMapper.pagedListCount(BoardlikeDto.PagedListReqDto.builder().deleted(false).boardId(param.getBoardId()).build());

        Board board = boardRepository.findById(param.getBoardId()).orElseThrow(() -> new RuntimeException("no data"));
        board.setCountlike(countlike);
        boardRepository.save(board);

        return DefaultDto.CreateResDto.builder().id((long) 0).build();
    }

    /**/

    @Override
    public DefaultDto.CreateResDto create(BoardlikeDto.CreateReqDto param) {
        return boardlikeRepository.save(param.toEntity()).toCreateResDto();
    }

    @Override
    public void update(BoardlikeDto.UpdateReqDto param) {
        Boardlike boardlike = boardlikeRepository.findById(param.getId()).orElseThrow(()->new RuntimeException("no data"));
        if(param.getDeleted() != null){ boardlike.setDeleted(param.getDeleted()); }
        boardlikeRepository.save(boardlike);
    }

    @Override
    public void delete(DefaultDto.DeleteReqDto param) {
        update(BoardlikeDto.UpdateReqDto.builder().id(param.getId()).deleted(true).build());
    }

    @Override
    public BoardlikeDto.DetailResDto detail(DefaultDto.DetailReqDto param) {
        BoardlikeDto.DetailResDto boardlike = boardlikeMapper.detail(param.getId());
        return boardlike;
    }

    @Override
    public List<BoardlikeDto.DetailResDto> list(BoardlikeDto.ListReqDto param) {
        return detailList(boardlikeMapper.list(param));
    }
    public List<BoardlikeDto.DetailResDto> detailList(List<BoardlikeDto.DetailResDto> list){
        List<BoardlikeDto.DetailResDto> newList = new ArrayList<>();
        for(BoardlikeDto.DetailResDto each : list){
            newList.add(detail(DefaultDto.DetailReqDto.builder().id(each.getId()).build()));
        }
        return newList;
    }

    @Override
    public DefaultDto.PagedListResDto pagedList(BoardlikeDto.PagedListReqDto param) {
        DefaultDto.PagedListResDto res = param.init(boardlikeMapper.pagedListCount(param));
        res.setList(detailList(boardlikeMapper.pagedList(param)));
        return res;
    }

    @Override
    public List<BoardlikeDto.DetailResDto> scrollList(BoardlikeDto.ScrollListReqDto param) {
        param.init();
        return detailList(boardlikeMapper.scrollList(param));
    }


}
