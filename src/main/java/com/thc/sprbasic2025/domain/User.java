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
public class User extends AuditingFields{
    String username;
    String password;
    String name;
    String nick;
    String phone;

    protected User(){}
    private User(Boolean deleted, String username, String password, String name, String nick, String phone){
        this.deleted = deleted;
        this.username = username;
        this.password = password;
        this.name = name;
        this.nick = nick;
        this.phone = phone;
    }
    public static User of(String username, String password, String name, String nick, String phone){
        return new User(false, username, password, name, nick, phone);
    }
    public DefaultDto.CreateResDto toCreateResDto() {
        return DefaultDto.CreateResDto.builder().id(getId()).build();
    }
}
