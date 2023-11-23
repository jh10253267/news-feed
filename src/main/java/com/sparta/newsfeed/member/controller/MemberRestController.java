package com.sparta.newsfeed.member.controller;

import com.sparta.newsfeed.member.dto.RequestProfileUpdateDto;
import com.sparta.newsfeed.member.dto.SignupDto;
import com.sparta.newsfeed.member.service.MemberService;
import com.sparta.newsfeed.security.service.MemberDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/api/user")
public class MemberRestController {
    private final MemberService memberService;
    @PutMapping("/{username}")
    public Map<String, Object> modifycontent(@PathVariable("username") String username
            , @RequestBody RequestProfileUpdateDto requestProfileUpdateDto
            , @AuthenticationPrincipal MemberDetailsImpl memberDetails) {
        Map<String, Object> map = new HashMap<>();
        memberService.updateMember(requestProfileUpdateDto, memberDetails);
        map.put("result","success");
        return map;
    }
    @PostMapping("/signup")
    public Map<String, Object> register(@RequestBody SignupDto signupDto) {
        Map<String, Object> map = new HashMap<>();
        memberService.signup(signupDto);
        map.put("result", "success");
        return map;
    }

}
