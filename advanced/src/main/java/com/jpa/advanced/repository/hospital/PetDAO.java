package com.jpa.advanced.repository.hospital;

import com.jpa.advanced.entity.hospital.Pet;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class PetDAO {

    @PersistenceContext
    private EntityManager entityManager;

//    추가
    public Pet save(Pet pet){
        entityManager.persist(pet);
        return pet;
    }

//    조회
    public Optional<Pet> findById(Long id){
        return Optional.ofNullable(entityManager.find(Pet.class, id));
    }

//    전체 조회
    public List<Pet> findAll(){
        String query = "select p from Pet p";
        return entityManager.createQuery(query, Pet.class).getResultList();
    }

//  삭제
    public Pet delete(Pet pet){
        entityManager.remove(pet);
        return pet;
    }
}
