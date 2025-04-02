package com.thc.sprbasic2025.dto;

import com.thc.sprbasic2025.domain.Board;
import lombok.*;

public class BoardDto {
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CreateReqDto {
        String title;
        String content;
        String author;

        public Board toEntity(){
            return Board.of(getTitle(), getContent(), getAuthor());
        }
    }
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class CreateResDto {
        Long id;
    }

    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class UpdateReqDto {
        Long id;
        Boolean deleted;

        String title;
        String content;
        String author;
        Integer countread;
    }
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DeleteReqDto {
        Long id;
    }
    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DetailReqDto {
        Long id;
    }

    @Getter @Setter @Builder @NoArgsConstructor @AllArgsConstructor
    public static class DetailResDto {
        Long id;
        Boolean deleted;

        String title;
        String content;
        String author;
        Integer countread;
    }
}
