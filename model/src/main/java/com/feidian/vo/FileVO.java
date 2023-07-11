package com.feidian.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FileVO {

    private Integer code;
    private String msg;

    private byte[] fileByte;


    public FileVO(String msg) {
        this.msg = msg;
    }

    public FileVO(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

}
