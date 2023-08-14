package com.jpa.basic.Repository;

import com.jpa.basic.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberDAO {
   @PersistenceContext
    private EntityManager entityManager;

    public Member save(Member member){
        entityManager.persist(member);
        return member;
    }
//   성능떨어지니까 Optional은 어지간하면 리턴타입으로만.
    public Optional<Member> findById(Long id){
        return Optional.ofNullable( entityManager.find( Member.class, id ));
    }

    public void delete(Member member){
        entityManager.remove(member);
    }
}
