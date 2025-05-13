package com.thc.sprbasic2025.domain;

import com.thc.sprbasic2025.dto.DefaultDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Entity
public class Board extends AuditingFields{
    Long userId; // FK!! 작성자를 이제는 author 가 아니라, 사용자 아이디값을 직접!!!

    String title;
    String content;
    /*String author;*/
    Integer countread;
    Integer countlike;

    // 다른 생성자 사용원하는데, 빈생성자를 안만들면 에러나니까 만들기!!
    protected Board(){}
    // 거의 전체 필드 다 반영한 생성자.. 하지만 of 메서드 로만 호출 될 수 있음!!
    private Board(Boolean deleted, Long userId, String title, String content, Integer countread, Integer countlike){
        this.deleted = deleted;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.countread = countread;
        this.countlike = countlike;
    }
    //Entity의 인스턴스를 만들때는 무조건 of 만 써주세요!!
    public static Board of(Long userId, String title, String content){
        return new Board(false, userId, title, content, 0, 0);
    }

    public DefaultDto.CreateResDto toCreateResDto() {
        return DefaultDto.CreateResDto.builder().id(getId()).build();
    }

}
