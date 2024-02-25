package com.app.mysecurity.repository;

import com.app.mysecurity.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.stream.DoubleStream;

public interface MemberRepository extends JpaRepository<Member, Long> ,MemberQueryDSL{
//    아이디로 전체 정보 조회
    public Optional<Member> findAllByMemberId(String memberId);

//  email로 전체 정보 조회
    public Optional<Member> findAllByMemberEmail(String memberEmail);

}
