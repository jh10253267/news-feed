package com.sparta.repository;

import com.sparta.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
    //Insert테스트를 위한 메소드(임시)
    Long countBy();
}
