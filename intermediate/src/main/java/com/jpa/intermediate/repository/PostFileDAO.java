package com.jpa.intermediate.repository;

import com.jpa.intermediate.file.PostFile;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class PostFileDAO {

    @PersistenceContext
    private EntityManager entityManager;

//    추가
    public PostFile save(PostFile postFile){
        entityManager.persist(postFile);
        return postFile;
    }

//    조회
    public Optional<PostFile> findById(Long id){
        return Optional.ofNullable(entityManager.find(PostFile.class,id));
    }

//    전체 조회
    public List<PostFile> findAll(){
        String query = "select p from PostFile p";
        return entityManager.createQuery(query,PostFile.class).getResultList();
    }

//    삭제
    public PostFile delete(PostFile postFile){
        entityManager.remove(postFile);
        return postFile;
    }


}
