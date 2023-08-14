package com.jpa.basic.Repository;

import com.jpa.basic.entity.Car;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public class CarDAO {
    @PersistenceContext
    private EntityManager entityManager;

    public Car save(Car car){
        entityManager.persist(car);
        return car;
    }

    public Optional<Car> findById(Long id){
       return Optional.ofNullable(entityManager.find(Car.class, id));
    }

    public boolean deleteById(Long id) {
        final Optional<Car> foundCar = findById(id);
        if(foundCar.isPresent()){
            entityManager.remove(foundCar.get());
            return true;
        }
        else{
            return false;
        }
    }
}
