package com.jpa.expert.repository.car;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CarDAO {
    @PersistenceContext
    private EntityManager entityManager;

//    차주 추가
//    차주 조회
//    차주 전체 조회
//    차주 삭제


}
