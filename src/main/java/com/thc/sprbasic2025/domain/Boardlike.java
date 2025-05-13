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
public class Boardlike extends AuditingFields{
    Long boardId;
    Long userId;

    protected Boardlike(){}
    private Boardlike(Boolean deleted, Long boardId, Long userId){
        this.deleted = deleted;
        this.boardId = boardId;
        this.userId = userId;
    }
    public static Boardlike of(Long boardId, Long userId){
        return new Boardlike(false, boardId, userId);
    }

    public DefaultDto.CreateResDto toCreateResDto() {
        return DefaultDto.CreateResDto.builder().id(getId()).build();
    }

}
