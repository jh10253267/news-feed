package com.sparta.repository;

import com.sparta.domain.Board;
import com.sparta.domain.Member;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Log4j2
class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    //@Autowired
    //BCryptPasswordEncoder passwordEncoder;

    @Autowired
    MemberRepository memberRepository;
    @DisplayName("[member]")
    @Test
    void testInsert() {
        int dataSize = 50;
        IntStream.rangeClosed(1, dataSize).forEach(i -> {
            Member member = Member.builder()
                    .username("username..."+i)
                    .password("passwordEncoder")
                    .build();
            Member result = memberRepository.save(member);
            log.info("Id: "  + result.getUsername());
        });

        //assertThat(memberRepository.countBy().isEqualTo(dataSize));
    }

    @DisplayName("[Member update]")
    @Test
    void testUpdate(){
        String username = "user...1";
        String password = "1234";

        Optional<Member> result = memberRepository.findById(username);

        Member member = result.orElseThrow();

        //setPassword(passwordEncoder.encode(password));

        Member updateMember = memberRepository.save(member);

        //assertThat(updatedMemeber.getPassword()).isEqualTo("1234");
    }

    @Transactional
    @Test
    @DisplayName("[Member] [Repository] [Delete]")
    void testDelete(){
        String username = "user..1";

        memberRepository.deleteById(username);

        Optional<Member> reuslt= memberRepository.findById(username);

        assertThatCode(reuslt::orElseThrow).isInstanceOf(NoSuchElementException.class);
    }

    @DisplayName("[Memeber] [Repository] [Update] [Password]")
    @Transactional
    @Test
    void givenContentStr_whenDoUpdate_thenReturnsUpdatedMemberContent(){
        String contentStr = "update test";
        String username = "user...1";

        Optional<Member> result=memberRepository.findById(username);
        Member member = result.orElseThrow();

        member.changeContent(contentStr);
        Member updatedMember = memberRepository.save(member);

        assertThat(updatedMember.getContent()).isEqualTo(contentStr);
    }
}