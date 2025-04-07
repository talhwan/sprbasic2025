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
    String title;
    String content;
    String author;
    Integer countread;

    // 다른 생성자 사용원하는데, 빈생성자를 안만들면 에러나니까 만들기!!
    protected Board(){}
    // 거의 전체 필드 다 반영한 생성자.. 하지만 of 메서드 로만 호출 될 수 있음!!
    private Board(Boolean deleted, String title, String content, String author, Integer countread){
        this.deleted = deleted;
        this.title = title;
        this.content = content;
        this.author = author;
        this.countread = countread;
    }
    //Entity의 인스턴스를 만들때는 무조건 of 만 써주세요!!
    public static Board of(String title, String content, String author){
        return new Board(false, title, content, author, 0);
    }

    public DefaultDto.CreateResDto toCreateResDto() {
        return DefaultDto.CreateResDto.builder().id(getId()).build();
    }
    /*
    @PrePersist
    public void onPrePersist() {
        this.deleted = false;
        this.countread = 0;
    }
    */

}
