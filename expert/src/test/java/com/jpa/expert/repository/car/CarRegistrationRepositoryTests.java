package com.jpa.expert.repository.car;


import com.jpa.expert.entity.car.Car;
import com.jpa.expert.entity.car.CarRegistration;
import com.jpa.expert.entity.type.CarBrand;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

@Transactional @Rollback(false) @SpringBootTest @Slf4j
public class CarRegistrationRepositoryTests {
    @Autowired
    private CarRegistrationRepository carRegistrationRepository;

    @Test
    public void findOwnerAvgAgeByCarBrandTest(){
     log.info(carRegistrationRepository.findOwnerAvgAgeByCarBrand(CarBrand.KIA)+ " ");
    }

    @Test
    public void findAllCarByAge(){
        carRegistrationRepository.findAllCarByAge().stream().map(Car::toString).forEach(log::info);
    }

    @Test
    public void findMax(){
        carRegistrationRepository.deleteByAge(90);
    }


}
