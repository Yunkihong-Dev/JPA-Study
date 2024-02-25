package com.app.security.repository;

import com.app.security.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberRepository extends JpaRepository<Member, Long> {
    public Member findByMemberIdAndMemberPassword(String memberId, String memberPassword);

}
