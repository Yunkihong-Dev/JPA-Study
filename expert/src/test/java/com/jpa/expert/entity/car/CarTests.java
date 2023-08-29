package com.jpa.expert.entity.car;

import com.jpa.expert.entity.type.CarBrand;
import com.jpa.expert.repository.car.CarDAO;
import com.jpa.expert.repository.car.CarOwnerDAO;
import com.jpa.expert.repository.car.CarRegistrationDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@Transactional @SpringBootTest @Slf4j
@Rollback(false)
public class CarTests {
    @Autowired
    private CarOwnerDAO carOwnerDAO;
    @Autowired
    private CarDAO carDAO;
    @Autowired
    private CarRegistrationDAO carRegistrationDAO;

    @Test
    public void saveTest(){
        Car car = new Car();
        CarOwner carOwner = new CarOwner ();
        CarOwnerAddress carOwnerAddress = new CarOwnerAddress ();
        CarRegistration carRegistration = new CarRegistration();

        car.setCarBrand (CarBrand.KIA);
        car.setCarName("Carnival");
        car.setCarPrice(700_000_000L);
        car.setCarReleaseDate (LocalDateTime.now());

        carOwnerAddress.setCarOwnerAddress("서울");
        carOwnerAddress.setCarOwnerAddressDetail("강남");
        carOwnerAddress.setCarOwnerZipcode("12345");
        carOwner.setCarOwnerAddress(carOwnerAddress);

        carOwner.setCarOwnerName("홍윤기");
        carOwner.setCarOwnerAge(20);

        carRegistration.setCarOwner(carOwner);
        carRegistration.setCar(car);

        carDAO.save(car);
        carOwnerDAO.save(carOwner);
        carRegistrationDAO.save(carRegistration);

    }

    @Test
    public void findByIdTest(){
        carDAO.findById(1L).ifPresentOrElse(car -> {
            log.info(car.toString());
        },()->{
            log.info("없는 자동차 입니다.");
        });

//      carDAO.findById(1L).orElseThrow(()->{throw new RuntimeException();});


    }

//    기존 자동차의 차주를 다른 차주로 변경
    @Test
    public void updateOwner(){
        Car car = carDAO.findById(1L).get();
        CarOwner carOwner = new CarOwner ();
        CarOwnerAddress carOwnerAddress = new CarOwnerAddress ();
        CarRegistration carRegistration = new CarRegistration();

        carOwner.setCarOwnerName("김동성");
        carOwner.setCarOwnerAge(20);

        carOwnerAddress.setCarOwnerAddress("서울");
        carOwnerAddress.setCarOwnerAddressDetail("관악");
        carOwnerAddress.setCarOwnerZipcode("07123");
        carOwner.setCarOwnerAddress(carOwnerAddress);

        carRegistration.setCarOwner(carOwner);
        carRegistration.setCar(car);

        carDAO.save(car);
        carOwnerDAO.save(carOwner);
        carRegistrationDAO.save(carRegistration);


    }






//
    @Test
    public void deleteTest(){
        final Optional<Car> foundCar = carDAO.findById(1L);
        foundCar.ifPresent(carDAO::delete);
    }


    @Test
    public void findAllTest() {
        carRegistrationDAO.findAll().forEach(carRegistration -> {
            log.info(carRegistration.toString());
        });

    }


}
