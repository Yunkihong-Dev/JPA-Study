package com.jpa.basic.repository;

import com.jpa.basic.Repository.MemberDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;


// Proxy가 중간이 비기에 전이가 안된다. transactional은 사용하는 쪽에서 걸어야한다.
@Transactional
@SpringBootTest @Slf4j
@Rollback(false)
public class MemberDAOTest {

    EntityManager entityManager;

    @Autowired
    private MemberDAO memberDAO;

    @Test
    public void saveTest(){

    }


}
