package com.jpa.basic.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jpa.basic.entity.Member;

import java.util.List;

public interface MemberRepository extends JpaRepository<Member, Long> {

//   쿼리메소드
//   메소드 이름으로 메소드를 만든다
    public List<Member> findByMemberName(String name);

    public List<Member> findByMemberNameContaining(String memberName);

    public List<Member> findByMemberNameStartingWith(String memberName);



}
