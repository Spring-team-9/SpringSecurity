package com.example.assignment_memo.util.ApiResponse;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class ApiResult<T> {
    private final LocalDateTime timestamp =  LocalDateTime.now();
    private final boolean success;
    private final HttpStatus status;
    private final String message;
    private final T response;
    private final CodeError error;
    public ApiResult(boolean success, T response, CodeError error){

        if (success){
            this.status = CodeError.OK.getHttpStatus();
            this.message = "성공";
        } else {
            this.status = error.getHttpStatus();
            this.message = error.getDetail();
        }
        this.success = success;
        this.response = response;
        this.error = error;
    }
}
