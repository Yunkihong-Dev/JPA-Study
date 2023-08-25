package com.jpa.expert.entity.car;

import com.jpa.expert.aduting.Period;
import com.jpa.expert.entity.type.CarBrand;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TBL_CAR")
@Getter @Setter @ToString
// 사용자 정의 SQL 삭제 구문으로 소프트 삭제를 수행합니다.
@SQLDelete(sql = "update tbl_car set deleted = true where id = ?")
// 하이버네이트 필터를 사용하여 삭제된 레코드를 제외합니다.
@Where(clause = "deleted = false")
public class Car extends Period {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String carName;

    // 열거형 데이터를 문자열로 저장합니다.
    @Enumerated(EnumType.STRING)
    private CarBrand carBrand;
    private Long carPrice;
    private LocalDateTime carReleaseDate;

    // 레코드가 삭제되었는지 여부를 나타내는 플래그입니다.
    private boolean deleted = Boolean.FALSE;

    @OneToMany(fetch = FetchType.LAZY ,mappedBy = "car")
    private List<CarRegistration> carRegistrations = new ArrayList<>();

    public void setCarRegistration(CarRegistration carRegistration){
        if (carRegistration.getCar()!= this){
            carRegistration.setCar(this);
        }
        this.carRegistrations.add(carRegistration);
    }
}
