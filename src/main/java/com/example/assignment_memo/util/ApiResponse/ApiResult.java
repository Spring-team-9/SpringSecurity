package com.example.assignment_memo.util.ApiResponse;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApiResult {
    private final LocalDateTime timestamp =  LocalDateTime.now();
    private final int status;
    private final String message;
    private final Object response;
    private final CodeError error;
    public ApiResult(CodeSuccess codeSuccess, Object response, CodeError error){

        if (codeSuccess != null){
            this.status = codeSuccess.getHttpStatus().value();
            this.message = codeSuccess.getDetail();
        } else {
            this.status = error.getHttpStatus().value();
            this.message = error.getDetail();
        }
        this.response = response;
        this.error = error;
    }
}