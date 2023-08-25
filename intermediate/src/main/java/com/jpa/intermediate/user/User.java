package com.jpa.intermediate.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter @Setter @ToString
@Table(name = "TBL_USER")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class User {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String userId;
    private String userPassword;
    private String userName;
    @Embedded
    private Address address;
}