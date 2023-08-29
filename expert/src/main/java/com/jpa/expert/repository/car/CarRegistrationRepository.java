package com.jpa.expert.repository.car;

import com.jpa.expert.entity.car.CarRegistration;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRegistrationRepository extends JpaRepository<CarRegistration,Long> {

    @EntityGraph(attributePaths= {"car", "carOwner"})
    public List<CarRegistration> findAll();

//    자동차가 FERRARI인 차주들의 평균 나이 구하기
//    자동차(id) 마다 전차주들과 현자주의 평균 나이가 601~70세 사이인 자동차 정보 조회
//    현차주의 나이가 90세 이상이면 자동차 폐차
}
