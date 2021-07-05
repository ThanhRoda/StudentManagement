package com.example.student.respon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseData<T> {
    int errorCode;
    T data;
    String message;

    public ResponseData(int code,T data) {
        this.data = data;
        this.errorCode = code;
        this.message ="";
    }
    public ResponseData(int code, String data) {
        this.data = null;
        this.errorCode =code;
        this.message = message;
    }

}
