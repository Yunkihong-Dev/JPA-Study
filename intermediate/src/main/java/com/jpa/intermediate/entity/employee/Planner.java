package com.jpa.intermediate.entity.employee;

import com.sun.istack.NotNull;
import lombok.ToString;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity @ToString
@Table(name = "TBL_PLANNER")
@DynamicInsert
@DiscriminatorValue("pln")
public class Planner extends Employee{
    private int plannerOaLevel;
    @ColumnDefault(value = "0")
    @NotNull private Integer clientCount;
}
