package com.jpa.advanced.repository.post;

import com.jpa.advanced.entity.post.Reply;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class ReplyDAO {
    @PersistenceContext
    private EntityManager entityManager;

    //    추가
    public Reply save(Reply reply) {
        entityManager.persist (reply);
        return reply;
    }
    //    조회
    public Optional<Reply> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Reply.class,id));
    }
    //    전체 조회
    public List<Reply> findAll(){
        String query = "select r from Reply r";
        return entityManager.createQuery(query, Reply.class).getResultList();
    }

    //    삭제
    public Reply delete(Reply reply){
        entityManager.remove(reply);
        return reply;
    }
}
