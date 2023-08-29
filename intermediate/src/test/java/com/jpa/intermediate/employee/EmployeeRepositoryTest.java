package com.jpa.intermediate.employee;


import com.jpa.intermediate.repository.DeveloperRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;


@SpringBootTest
@Slf4j
@Rollback(false) @Transactional
public class EmployeeRepositoryTest {
    @Autowired
    public DeveloperRepository developerRepository;

    @Test
    public void findAllDevelopers(){
//        developerRepository.findAllDevelopers().stream().map(developer -> developer.toString()).forEach(log::info);
    }
}
