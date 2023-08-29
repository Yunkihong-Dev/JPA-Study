package com.jpa.intermediate.entity.user;


import com.jpa.intermediate.repository.CompanyDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

@SpringBootTest
@Slf4j
@Rollback(false)
@Transactional
public class CompanyDaoTest {

    @Autowired
    CompanyDAO companyDAO;

    @Test
    public void saveTest(){
        Company company = new Company();
        Address address = new Address();
        company.setUserName("안동대학교");
        company.setUserId("andongNational");
        company.setUserPassword("andong123");
        address.setPostcode("12345");
        address.setAddress("서울시 관악구 봉천동 1605-31");
        address.setAddressDetail("202호");
        company.setAddress(address);
        companyDAO.save(company);
    }
    @Test
    public void findAllTest(){
        companyDAO.findAll().stream().map(Company::toString).forEach(log::info);
    }

}

