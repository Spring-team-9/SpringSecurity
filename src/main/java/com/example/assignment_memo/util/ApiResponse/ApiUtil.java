package com.example.assignment_memo.util.ApiResponse;

public class ApiUtil {
    public static <T> ApiResult<T> successResponse(T response){
        return new ApiResult<>(true, response, null);
    }

    public static ApiResult<?> errorResponse(CodeError codeError){
        return new ApiResult<>(false, null, codeError);
    }
}
