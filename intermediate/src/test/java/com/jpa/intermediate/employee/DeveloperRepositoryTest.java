package com.jpa.intermediate.employee;


import com.jpa.intermediate.entity.employee.Developer;
import com.jpa.intermediate.repository.DeveloperRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.time.LocalDate;

@SpringBootTest
@Slf4j
@Rollback(false) @Transactional
public class DeveloperRepositoryTest {
    @Autowired
    private DeveloperRepository developerRepository;

    @Test
    public void saveTest() {
        Developer developer = new Developer();
        developer.setName("홍윤기");
        developer.setCareer(7);
        developer.setBirth(LocalDate.of(1980, 12, 4));
        developer.setProjectCount(10);
        developer.setDeveloperLevel(3);
        developerRepository.save(developer);
    }

    @Test
    public void findByDeveloperLevelGreaterThanEqualTest(){
        log.info(developerRepository.findByDeveloperLevelGreaterThanEqual(3).toString());
    }
    @Test
    public void findByEmployeeBirthOf80(){
        log.info(developerRepository.findByEmployeeBirthOf80().get(0).toString());
    }
}
