package com.jpa.advanced.repository.hospital;

import com.jpa.advanced.entity.hospital.Owner;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class OwnerDAO {
    @PersistenceContext
    private EntityManager entityManager;

    //    추가
    public Owner save(Owner owner){
        entityManager.persist(owner);
        return owner;
    }

    //    조회
    public Optional<Owner> findById(Long id){
        return Optional.ofNullable(entityManager.find(Owner.class, id));
    }

    //    전체 조회
    public List<Owner> findAll(){
        String query = "select o from Owner o";
        return entityManager.createQuery(query, Owner.class).getResultList();
    }

    //  삭제
    public Owner delete(Owner owner){
        entityManager.remove(owner);
        return owner;
    }
}
