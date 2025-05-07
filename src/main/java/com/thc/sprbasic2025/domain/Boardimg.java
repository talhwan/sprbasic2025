package com.thc.sprbasic2025.domain;

import com.thc.sprbasic2025.dto.DefaultDto;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Entity
public class Boardimg extends AuditingFields{
    Long boardId;
    String url;

    protected Boardimg(){}
    private Boardimg(Boolean deleted, Long boardId, String url){
        this.deleted = deleted;
        this.boardId = boardId;
        this.url = url;
    }
    public static Boardimg of(Long boardId, String url){
        return new Boardimg(false, boardId, url);
    }

    public DefaultDto.CreateResDto toCreateResDto() {
        return DefaultDto.CreateResDto.builder().id(getId()).build();
    }

}
