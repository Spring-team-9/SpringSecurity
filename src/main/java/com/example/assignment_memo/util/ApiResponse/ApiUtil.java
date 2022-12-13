package com.example.assignment_memo.util.ApiResponse;

import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiUtil {
    public static ApiResult successResponse(CodeSuccess codeSuccess, Object response){
        return new ApiResult(codeSuccess, response, null);
    }

//    public static ApiResult successResponse(CodeSuccess codeSuccess){ // 반환 데이터 없는 케이스
//
//        return new ApiResult(codeSuccess, null, null);
//    }

    public static ApiResult errorResponse(CodeError codeError){
        return new ApiResult(null, null, codeError);
    }
}


