package com.example.assignment_memo.util.security;

import com.example.assignment_memo.entity.User;
import com.example.assignment_memo.repository.UserRepository;
import com.example.assignment_memo.util.ApiResponse.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.example.assignment_memo.util.ApiResponse.CodeError.MEMBER_NOT_FOUND;

@Service
@RequiredArgsConstructor
// Impl은 Implements의 약자로 구현클래스 접미사로 사용
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)                 // 해당 유저네임으로 정보 찾기
                .orElseThrow(()-> new CustomException(MEMBER_NOT_FOUND));   // 없으면 404에러

        return new UserDetailsImpl(user, user.getUsername());
    }
}

