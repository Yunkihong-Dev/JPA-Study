package com.jpa.expert.entity.car;

import com.jpa.expert.aduting.Period;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "TBL_CAR_REGISTRATION")
@Getter
@Setter
@ToString
public class CarRegistration extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Car car;

    @ManyToOne(fetch = FetchType.LAZY)
    private CarOwner carOwner;
}
