package com.jpa.intermediate.entity.employee;

import com.sun.istack.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "TBL_EMPLOYEE")
@ToString @Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //상속관계시 부모 엔티티에 작성하며, 기본 전략은 SINGLE_TABLE이다.
@DiscriminatorColumn(name = "DEPARTMENT")
public class Employee {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull
    private String name;
    private LocalDate birth;
    @NotNull private int career;
}
