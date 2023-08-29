package com.jpa.intermediate.entity.user;

import com.jpa.intermediate.repository.CompanyRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@SpringBootTest
@Slf4j
@Rollback(false)
public class CompanyRepositoryTest {
    @Autowired
    private CompanyRepository companyRepository;

    @Test
    public void saveTest(){
        Company company = new Company();
        Address address = new Address();
        company.setBusinessNumber("1231123131233");
        company.setUserName("카카오");
        company.setUserId("kakao");
        address.setAddress("경기도");
        company.setAddress(address);
        company.setUserPassword("211123123233");
        companyRepository.save(company);

    }

    @Test
    public void updateTest(){
//        findById : 쿼리 발생 전 1차 캐시 검사 후 존재하면 1차 캐시 객체로 사용
//        findAll : 쿼리 발생  1차 캐시 검사 후 존재하면 1차 캐시 객체로 사용
       List<Company> companies=companyRepository.findAll();
        companyRepository.updateUserIdByAddress("1","경기도");
        companyRepository.updateUserIdByAddress("3","서울");
        companies =  companyRepository.findAll();
        log.info(companies.toString());

    }

    @Test
    public void auditingTest(){
        companyRepository.findById(1L).get().setUserName("updated") ;
    }

}
