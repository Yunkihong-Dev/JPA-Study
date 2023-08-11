package com.jpa.basic.entity;

import com.jpa.basic.type.CarBrandType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @ToString @Setter
@Table(name = "TBL_CAR")
public class Car {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String carName;
    @Enumerated(value = EnumType.STRING)
    private CarBrandType carBrandType;
    private LocalDateTime carReleaseDate;
    
}
