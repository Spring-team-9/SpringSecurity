package com.example.team9_SpringSecurity.controller;

import com.example.team9_SpringSecurity.dto.LoginRequestDto;
import com.example.team9_SpringSecurity.dto.MessageDto;
import com.example.team9_SpringSecurity.dto.SignupRequestDto;
import com.example.team9_SpringSecurity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RequestMapping("/api/user")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // ---------------회원가입/로그인 기능
    // 회원가입
    @PostMapping("/signup")
    public ResponseEntity<MessageDto> signup(@RequestBody @Valid SignupRequestDto dto) {
        MessageDto messageDto = userService.signup(dto);
        return new ResponseEntity<>(messageDto, HttpStatus.OK);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<MessageDto> login(@RequestBody @Valid LoginRequestDto dto, HttpServletResponse response) {
        MessageDto messageDto = userService.login(dto, response);
        return new ResponseEntity<>(messageDto, HttpStatus.OK);
    }

    // 로그인
    @DeleteMapping("/login")
    public ResponseEntity<MessageDto> deleteAccount(@RequestBody @Valid LoginRequestDto dto, HttpServletRequest request) {
        MessageDto messageDto = userService.delete(dto, request);
        return new ResponseEntity<>(messageDto, HttpStatus.OK);
    }
}