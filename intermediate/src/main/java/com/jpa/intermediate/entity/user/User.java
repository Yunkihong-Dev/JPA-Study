package com.jpa.intermediate.entity.user;

import com.jpa.intermediate.aduting.Period;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter @Setter @ToString
@Table(name = "TBL_USER")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User extends Period { // abstract는 상속관계에서 부모 엔티티를 단독으로 조회하지 말라는 뜻
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String userId;
    private String userPassword;
    private String userName;
    @Embedded
    private Address address;
}