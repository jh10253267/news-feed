package com.sparta.newsfeed.member.repository;

import com.sparta.newsfeed.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, String> {
    //Insert테스트를 위한 메소드(임시)
    Long countBy();
    Optional<Member> findByUsername(String username);

}
