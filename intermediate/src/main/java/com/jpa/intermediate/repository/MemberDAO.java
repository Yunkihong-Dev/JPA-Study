package com.jpa.intermediate.repository;

import com.jpa.intermediate.entity.user.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberDAO {
    private EntityManager entityManager;

//  추가
    public Member save(Member member){
    entityManager.persist(member);
    return member;
    }

//    id로 조회
    public Optional<Member> findById(Long id){
        return Optional.ofNullable(entityManager.find(Member.class, id));
    }

//    전체 조회
    public List<Member> findAll(){
        String query = "select m from Member m";
        return entityManager.createQuery(query, Member.class).getResultList();
    }

//  삭제
    public Member delete(Member member){
        entityManager.remove(member);
        return member;
    }
}
