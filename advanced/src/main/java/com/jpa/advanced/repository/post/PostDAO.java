package com.jpa.advanced.repository.post;

import com.jpa.advanced.entity.post.Post;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class PostDAO {
    @PersistenceContext
    private EntityManager entityManager;

//    추가
    public Post save(Post post) {
        entityManager.persist (post);
        return post;
    }
//    조회
    public Optional<Post> findById(Long id) {
        return Optional.ofNullable(entityManager.find(Post.class,id));
    }
//    전체 조회
    public List<Post> findAll(){
        String query = "select p from Post p";
        return entityManager.createQuery(query, Post.class).getResultList();
    }

//    삭제
    public Post delete(Post post){
        entityManager.remove(post);
        return post;
    }
}
