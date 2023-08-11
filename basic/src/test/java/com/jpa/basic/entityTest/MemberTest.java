package com.jpa.basic.entityTest;


import com.jpa.basic.Repository.MemberDAO;
import com.jpa.basic.entity.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest @Slf4j
public class MemberTest {
    @Autowired
    private MemberDAO memberDAO;

    @PersistenceContext
    private EntityManager entityManager;


    @Test
    public void findMemberAllTest(){
        log.info(memberDAO.findAll().toString());
    }
    @Test
    @Transactional
    @Rollback(false) //기본적으로 rollback은 true값이기에 통과하더라도 rollback되어 쿼리문도 안나가고 db에 반영이 안된다.
    public void entityTest(){
//        Member member1 = new Member();
//        member1.setMemberName("홍윤기");
//        member1.setMemberEmail("ricky0130@naver.com");
//        member1.setMemberAge(20);
//        member1.setMemberPassword("1234");
//        member1.setMemberType(MemberType.MEMBER);
//        LocalDateTime dateTime = LocalDateTime.now();
//        member1.setGeneratedDate(dateTime);
//
//        entityManager.persist(member1);
//        Member member2 = new Member();
//
//        member2.setMemberName("홍윤기2");
//        member2.setMemberEmail("ricky013000@naver.com");
//        member2.setMemberAge(20);
//        member2.setMemberPassword("1234");
//        member2.setMemberType(MemberType.ADMIN);
//        LocalDateTime dateTime2 = LocalDateTime.now();
//        member2.setGeneratedDate(dateTime2);
//
//        entityManager.persist(member2);

        Member foundMember = entityManager.find(Member.class, 16L);
//        1차 캐시에 조회된 엔티티가 이미 있다면,SQL 조회 없이 1차 캐시에서 조회된다.
        final Member foundMember2 = entityManager.find(Member.class, 16L);
//    1차 캐시에 등록된 엔티티가 있다면, 동일성이 보장된다.
        assertThat(foundMember).isEqualTo(foundMember2);
        foundMember.setMemberAge(31);
//      1차 캐시는 그대로 유지하되, 쓰기 지연 저장소에 있었던 SQL을 즉시 DB로 전송
//      commit() 전에 실행하고자 사용한다.
        entityManager.flush();
        entityManager.clear();


        log.info("flushTest");
//        update 쿼리 이전에 clear()되기 떄문에 flush() 후 clear()를 진행해야 한다.
//        entityManager.clear();

        foundMember = entityManager.find(Member.class, 16L);

//      기본적으로 쿼리가 나가는 순서 : find update create delete
      entityManager.remove(foundMember);

    }
//    여기서 오류가 발생하면 쿼리가 나가다가도 롤백이 되고, 오류가 안발생하면 정상적으로 커밋이 되며 트랜잭션이 로그에 저장됨.
//    Rollback을 할 경우 이전 커밋으로 돌아갈 수 있음

}
