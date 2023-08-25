package com.jpa.advanced.repository.member;

import com.jpa.advanced.entity.member.File;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.lang.reflect.Member;
import java.util.List;
import java.util.Optional;

@Repository
public class FileDAO {
    @PersistenceContext
    private EntityManager entityManager;

    //    회원 추가
    public File save(File file) {
        entityManager.persist(file);
        return file;
    }

    //    회원 조회
    public Optional<File> findById(Long id){
        return Optional.ofNullable(entityManager.find(File.class, id));
    }

    //   전체 회원 조회
    public List<File> findAll(){
        String query = "select f from File f";
        return entityManager.createQuery(query, File.class).getResultList();
    }

    //   회원 삭제
    public File delete(File file){
        entityManager.remove(file);
        return file;
    }
}
