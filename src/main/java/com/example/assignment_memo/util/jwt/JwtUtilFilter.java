package com.example.assignment_memo.util.jwt;

import com.example.assignment_memo.util.error.CustomException;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.example.assignment_memo.util.error.ErrorCode.INVALID_TOKEN;

@Slf4j
@RequiredArgsConstructor
public class JwtUtilFilter extends OncePerRequestFilter {
    private final JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = jwtUtil.resolveToken(request);

        if(token != null) {
            if(!jwtUtil.validateToken(token)){
//                jwtExceptionHandler(response, "Token Error", HttpStatus.UNAUTHORIZED.value());
//                return;

                throw new CustomException(INVALID_TOKEN);  // 이부분 수정해야함***
            }
            Claims info = jwtUtil.getUserInfoFromToken(token);
            setAuthentication(info.getSubject());
        }
        filterChain.doFilter(request,response);
    }

    public void setAuthentication(String username) {
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        Authentication authentication = jwtUtil.createAuthentication(username);
        context.setAuthentication(authentication);

        SecurityContextHolder.setContext(context);
    }

//    public void jwtExceptionHandler(HttpServletResponse response, String msg, int statusCode) {
//        response.setStatus(statusCode);
//        response.setContentType("application/json");
//        try {
////            String json = new ObjectMapper().writeValueAsString(new SecurityExceptionDto(statusCode, msg));
////            response.getWriter().write(json);
//
//        } catch (Exception e) {
//            log.error(e.getMessage());
//        }
//    }

}