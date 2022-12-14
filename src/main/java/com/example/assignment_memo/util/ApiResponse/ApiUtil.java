package com.example.assignment_memo.util.ApiResponse;

import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiUtil {

    // 성공 응답 (반환 데이터 존재)
    public static ApiResult successResponse(CodeSuccess codeSuccess, Object response){
        return new ApiResult(codeSuccess, response, null);
    }

    // 성공 응답 (반환 데이터 없음)
    public static ApiResult successResponse(CodeSuccess codeSuccess){
        return new ApiResult(codeSuccess, null, null);
    }

    // 에러 응답
    public static ApiResult errorResponse(CodeError codeError){
        return new ApiResult(null, null, codeError);
    }
}


