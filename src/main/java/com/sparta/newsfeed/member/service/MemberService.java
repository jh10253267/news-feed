package com.sparta.newsfeed.member.service;

import com.sparta.newsfeed.member.domain.Member;
import com.sparta.newsfeed.member.dto.RequestProfileUpdateDto;
import com.sparta.newsfeed.member.dto.SignupDto;
import com.sparta.newsfeed.member.repository.MemberRepository;
import com.sparta.newsfeed.security.service.MemberDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
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
    public void updateMember(RequestProfileUpdateDto updateProfileDto, MemberDetailsImpl memberDetails){
        String loginUsername = memberDetails.getUsername();
        String updateProfileUsername = updateProfileDto.getUsername();

        if(!loginUsername.equals(updateProfileUsername)){
            throw new IllegalArgumentException("해당 로그인 유저네임과 변경하려는 사용자의 유저네임이 다릅니다.");
        }

        Member memberEntity = memberRepository.findById(updateProfileUsername).orElseThrow(()-> new IllegalArgumentException("해당 아이디는 찾을 수 없습니다."));

        String rawPassword = updateProfileDto.getPassword();
        // 첫번째 입력받은 비밀번호
        String rawPasswordConfirm = updateProfileDto.getPasswordConfirm();
        // 두 번째 입력받은 비밀번호
        if (StringUtils.isNotBlank(rawPassword) || StringUtils.isNotBlank(rawPasswordConfirm))  {
            // 비밀번호 일치 확인
            if (!rawPassword.equals(rawPasswordConfirm)) {
                throw new IllegalArgumentException("첫 번째 비밀번호와 확인 비밀번호가 일치하지 않습니다.");
            }
        }
        // 새 비밀번호를 사용하여 비밀번호 업데이트
        String bcrytPassword = passwordEncoder.encode(rawPassword);

        memberEntity.changePassword(bcrytPassword);
        memberEntity.changeContent(updateProfileDto.getContent());
        // change함수 활용해서 password, content를 memberEntity에 저장

        memberRepository.save(memberEntity);
    }
}
