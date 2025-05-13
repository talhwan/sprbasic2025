package com.thc.sprbasic2025.dto;

import com.thc.sprbasic2025.domain.Boardlike;
import lombok.*;
import lombok.experimental.SuperBuilder;

public class BoardlikeDto {

    /**/

    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CreateReqDto {
        Long boardId;
        Long userId;

        public Boardlike toEntity(){
            return Boardlike.of(getBoardId(), getUserId());
        }
    }

    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class UpdateReqDto extends DefaultDto.UpdateReqDto{
        String userId;
    }

    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class DetailResDto extends DefaultDto.DetailResDto{
        Long boardId;
        Long userId;
    }

    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class ListReqDto extends DefaultDto.ListReqDto{
        Long boardId;
        Long userId;
    }
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class PagedListReqDto extends DefaultDto.PagedListReqDto{
        Long boardId;
        Long userId;
    }
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class ScrollListReqDto extends DefaultDto.ScrollListReqDto{
        Long boardId;
        Long userId;
    }
}
