package com.jpa.intermediate.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity @Getter @ToString @Setter
@Table(name = "TBL_PRODUCT")
@NoArgsConstructor
public class Product {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String productName;
    @ColumnDefault(value="0")
    private Integer productPrice;
    @ColumnDefault(value="0")
    private Integer productStock;




}
