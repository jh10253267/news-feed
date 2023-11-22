package com.sparta.newsfeed.member.service;

import com.sparta.newsfeed.member.domain.Member;
import com.sparta.newsfeed.member.dto.RequestProfileUpdateDto;
import com.sparta.newsfeed.member.dto.SignupDto;
import com.sparta.newsfeed.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    @Transactional
    public void signup(SignupDto signupDto){
        String username = signupDto.getUsername();
        String bcrytPassword = passwordEncoder.encode(signupDto.getPassword());
        String content = signupDto.getContent();
        Optional<Member> checkUsername = memberRepository.findById(username);
        // email과 username은 중복되면 안됨
        if(checkUsername.isPresent()){
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        // 사용자 등록
        Member member = Member.builder()
                .username(username)
                .password(bcrytPassword)
                .content(content)
                .build();

        memberRepository.save(member);
    }

    @Transactional
    public Member updateMember(RequestProfileUpdateDto profileUpdateDto){
        Member memberEntity = memberRepository.findById(profileUpdateDto.getUsername())
                .orElseThrow(()->{
                    throw new IllegalArgumentException("유저를 찾을 수 없습니다.");
                });
        memberEntity.changeContent(profileUpdateDto.getContent());
        memberRepository.save(memberEntity);
        return memberEntity;
    }
}
