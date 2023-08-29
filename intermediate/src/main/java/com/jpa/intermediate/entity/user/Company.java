package com.jpa.intermediate.entity.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "TBL_COMPANY")
@Getter @Setter @ToString(callSuper = true)
public class Company extends User{
    private String businessNumber;
}
