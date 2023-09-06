package com.jpa.expert.repository.car;

import com.jpa.expert.entity.car.Car;
import com.jpa.expert.entity.car.CarRegistration;
import com.jpa.expert.entity.type.CarBrand;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRegistrationRepository extends JpaRepository<CarRegistration,Long> {

    @EntityGraph(attributePaths= {"car", "carOwner"})
    public List<CarRegistration> findAll();

//    자동차 브랜드별 차주들의 평균 나이 구하기
    @Query("select avg(r.carOwner.carOwnerAge) from CarRegistration r where r.car.carBrand =:carBrand")
    public double findOwnerAvgAgeByCarBrand(@Param("carBrand") CarBrand carBrand);
//    자동차(id) 마다 전차주들과 현자주의 평균 나이가 60~70세 사이인 자동차 정보 조회

//    fetch join을 직접 작성할 때에는 연관 객체로 작성하면 안된다.
//    fetch join을 사용할 때에는 직접 on 절을 작성해주어야 한다.
    @Query(" select c  from Car c  where c.id in (select r.car.id from CarRegistration r group by r.car.id having avg(r.carOwner.carOwnerAge) between 60 and 70)  ")
    public List<Car> findAllCarByAge();

//    현차주의 나이가 90세 이상이면 자동차 폐차
//   네이티브 쿼리는 soft delete가 안나가니 직접 해줘야한다.
//    DELETE, UPDATE 쿼리 작성 시 꼭 QModifying을 붙어주지
    @Modifying
    @Query(value = "UPDATE TBL_CAR SET DELETED = 1 WHERE ID IN  " +
                "(" +
                "SELECT CAR_ID FROM TBL_CAR_REGISTRATION WHERE CAR_OWNER_ID IN " +
                    "(" +
                    "SELECT ID  FROM TBL_CAR_OWNER WHERE ID IN " +
                        "(" +
                        "SELECT CAR_OWNER_ID FROM TBL_CAR_REGISTRATION WHERE " +
                        " (CAR_ID,CREATED_DATE )IN " +
                            "(" +
                            "SELECT CAR_ID , MAX(CREATED_DATE) CREATED_DATE FROM TBL_CAR_REGISTRATION GROUP BY CAR_ID " +
                            ") " +
                        ")" +
                    " AND CAR_OWNER_AGE >=:carOwnerAge " +
                    ")" +
                ")",
            nativeQuery=true)
    public void deleteByAge(int carOwnerAge);



}

