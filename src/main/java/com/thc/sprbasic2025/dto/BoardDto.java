package com.thc.sprbasic2025.dto;

import com.thc.sprbasic2025.domain.Board;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

public class BoardDto {
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CreateReqDto {
        Long userId;

        String title;
        String content;
        List<String> imgs;

        public Board toEntity(){
            return Board.of(getUserId(), getTitle(), getContent());
        }
    }
    /*@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CreateResDto {
        Long id;
    }*/

    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class UpdateReqDto extends DefaultDto.UpdateReqDto{
        Long userId;
        String title;
        String content;
        Integer countread;
    }
    /*@Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DeleteReqDto {
        Long id;
    }
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DetailReqDto {
        Long id;
    }*/

    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class DetailResDto extends DefaultDto.DetailResDto{
        Long userId;
        String title;
        String content;
        /*String author;*/
        Integer countread;
        Integer countlike;
        List<BoardimgDto.DetailResDto> imgs;

        String userUsername;
        String userName;
        String userNick;

        Boolean liked;
    }

    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class ListReqDto extends DefaultDto.ListReqDto{
        Long userId;
        String title;
    }
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class PagedListReqDto extends DefaultDto.PagedListReqDto{
        Long userId;
        String title;
    }
    @Getter @Setter @SuperBuilder @NoArgsConstructor @AllArgsConstructor
    public static class ScrollListReqDto extends DefaultDto.ScrollListReqDto{
        Long userId;
        String title;
    }
}
