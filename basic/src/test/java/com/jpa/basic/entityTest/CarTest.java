package com.jpa.basic.entityTest;

import com.jpa.basic.entity.Car;
import com.jpa.basic.type.CarBrandType;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.time.LocalDateTime;

@SpringBootTest @Slf4j
@Setter
public class CarTest {

    @PersistenceContext
    private EntityManager entityManager;


    @Transactional @Rollback(false) @Test
    public void carTest(){
        Car car1 = new Car();
        car1.setCarName("붕붕이2");
        car1.setCarBrandType(CarBrandType.HYUNDAI);
        LocalDateTime localDateTime = LocalDateTime.now();
        car1.setCarReleaseDate(localDateTime);
        entityManager.persist(car1);
//        entityManager.flush();
        entityManager.clear();

        final Car car2 =  entityManager.find(Car.class, 32L);

        car2.setCarBrandType(CarBrandType.BMW);

    }


}
