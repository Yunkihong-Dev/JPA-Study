package com.jpa.intermediate.repository;

import com.jpa.intermediate.user.Company;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Repository
public class CompanyDAO {
    @PersistenceContext
    private EntityManager entityManager;

    //  추가
    public Company save(Company company){
        entityManager.persist(company);
        return company;
    }

    //    id로 조회
    public Optional<Company> findById(Long id){
        return Optional.ofNullable(entityManager.find(Company.class, id));
    }

    //    전체 조회
    public List<Company> findAll(){
        String query = "select c from Company c";
        return entityManager.createQuery(query, Company.class).getResultList();
    }

    //  삭제
    public Company delete(Company company){
        entityManager.remove(company);
        return company;
    }
}
