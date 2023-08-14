package com.jpa.basic.repository;

import com.jpa.basic.Repository.CarDAO;
import com.jpa.basic.entity.Car;
import com.jpa.basic.type.CarBrandType;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest @Slf4j @Transactional @Rollback(false)
public class CarDAOTest {

    EntityManager entityManager;

    @Autowired
    CarDAO carDAO;

//  생성
   @Test
    public void saveTest(){
        Car car = new Car();
        car.setCarName("뿡뿡이");
        car.setCarBrandType(CarBrandType.PORSCHE);
        LocalDateTime dateTime = LocalDateTime.now();
        car.setCarReleaseDate(dateTime);
        carDAO.save(car);
    }
    public void updateTest () {
        carDAO.findById(30L).ifPresent(car -> {
            car.setCarBrandType(CarBrandType.BMW);
            assertThat(car.getCarBrandType()).isEqualTo(CarBrandType.KIA);
        });
    }
//  조회
    @Test
    public void findById(Long id) {
        carDAO.findById(30L).ifPresent(car-> assertThat(car.getCarName()).isEqualTo("RED")) ;
    }
//  삭제
    @Test
    public void deleteTest(){
        log.info( carDAO.deleteById(30L) ? "삭제 성공" : "삭제 실패" );
    }

}
