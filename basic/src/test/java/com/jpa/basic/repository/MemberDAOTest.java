package com.jpa.basic.repository;

import com.jpa.basic.Repository.MemberDAO;
import com.jpa.basic.entity.Member;
import com.jpa.basic.type.MemberType;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;


// Proxy가 중간이 비기에 전이가 안된다. transactional은 사용하는 쪽에서 걸어야한다.
@Transactional
@SpringBootTest @Slf4j
@Rollback(false)
public class MemberDAOTest {

    EntityManager entityManager;

    @Autowired
    private MemberDAO memberDAO;

    @Test
    public void saveTest(){
        Member member = new Member();
        member.setMemberName("홍윤기");
        member.setMemberEmail("reiasdaa@naver.com");
        member.setMemberAge(20);
        member.setMemberPassword("1234");
        member.setMemberType(MemberType.ADMIN);
        LocalDateTime dateTime = LocalDateTime.now();
        member.setGeneratedDate(dateTime);
        memberDAO.save(member);
    }
    @Test
    public void findByIdTest(){
        final Optional<Member> foundMember = memberDAO.findById(42L);
        foundMember.ifPresentOrElse(
                member-> Assertions.assertThat(member.getMemberAge()).isEqualTo(210)
                ,()->{log.info("존재하지 않는 회원입니다."); });
    }
    @Test
    public void updateTest(){
        final Optional<Member> foundMember = memberDAO.findById(42L);
        foundMember.ifPresentOrElse(member -> member.setMemberAge(30),()->{log.info("없는 회원");});
    }

    @Test
    public void deleteTest(){
        memberDAO.findById(42L).ifPresentOrElse((memberDAO::delete),()->{log.info("해당 유저가 없음");});
    }
}
