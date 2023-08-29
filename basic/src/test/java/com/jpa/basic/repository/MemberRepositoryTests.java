package com.jpa.basic.repository;

import com.jpa.basic.Repository.MemberRepository;
import com.jpa.basic.type.MemberType;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import com.jpa.basic.entity.Member;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.PATH;


@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;
    @Test
    public   void saveTest(){
        Member member = new Member();
        member.setMemberName("홍윤기");
        member.setMemberAge(20);
        member.setMemberType(MemberType.ADMIN);
        member.setMemberEmail("213@naver.com");
        member.setMemberPassword("11231");

        memberRepository.save(member);
    }

    @Test
    public void findByIdTest(){
    memberRepository.findById(1L).map(Member::toString).ifPresent(log::info);
    }

    @Test
    public void findAllTest(){
        assertThat(memberRepository.findAll()).hasSize(1);
    }
    @Test
    public void findByMemberNameContainingTest(){
        log.info(memberRepository.findByMemberNameContaining("홍").get(0).toString());
    }
    @Test
    public void findByMemberMemberNameStartingWithTest(){
        log.info(memberRepository.findByMemberNameStartingWith("홍").get(0).toString());
    }

    @Test
    public void updateTest(){
       memberRepository.findById(1L).ifPresent(member -> member.setMemberPassword("1234"));
    }

    @Test
    public void deleteTest(){
        memberRepository.deleteById(1L);
    }
    @Test
    public void findNameTest(){
        log.info(memberRepository.findByMemberName("홍윤기").get(0).toString());
    }
}
