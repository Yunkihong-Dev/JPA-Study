package com.jpa.intermediate.employee;

import com.jpa.intermediate.entity.employee.Developer;
import com.jpa.intermediate.entity.employee.Employee;
import com.jpa.intermediate.entity.employee.Planner;
import com.jpa.intermediate.repository.EmployeeDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.time.LocalDate;

@SpringBootTest @Slf4j
@Transactional
@Rollback(false)
public class EmployeeDAOTest {

    @Autowired
    private EmployeeDAO employeeDAO;
//  저장
    @Test
    public void saveTest(){
        Employee employee = new Employee();
        Developer developer = new Developer();
        Planner planner = new Planner();

        developer.setName("홍윤기");
        developer.setBirth(LocalDate.of(2000,1,30));
        developer.setCareer(10);
        developer.setDeveloperLevel(1);

        planner.setName("한동석");
        planner.setBirth(LocalDate.of (  2000, 12, 4));
        planner.setCareer(1);
        planner.setPlannerOaLevel(1);
        planner.setClientCount(1500);

        employeeDAO.save(developer);
        employeeDAO.save(planner);
    }
//  조회
    @Test
    public void updateTest(){
        employeeDAO.findAllByDeveloper(10).stream().map(Developer::toString).forEach(log::info);
    }

//  전체 조회
//  수정
//  삭제
}
