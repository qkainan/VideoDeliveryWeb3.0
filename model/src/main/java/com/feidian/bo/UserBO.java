package com.feidian.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserBO {

    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String email;

    private String phone;
    private String headUrl;
    private String userDescription;

    public UserBO(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }

    public UserBO(String username, String password, String nickname, String email) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
    }

    public UserBO(Long id, String headUrl, String userDescription){
        this.id = id;
        this.headUrl = headUrl;
        this.userDescription = userDescription;
    }

    public UserBO(Long id,String userDescription){
        this.id = id;
        this.userDescription = userDescription;
    }

}
