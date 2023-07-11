package com.feidian.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignupDTO {

    private String username;
    private String password;
    private String rePwd;
    private String nickname;


    private String verifyCode;
    private String emailAddress;

}
