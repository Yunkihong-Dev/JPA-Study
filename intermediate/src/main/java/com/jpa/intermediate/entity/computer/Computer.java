package com.jpa.intermediate.entity.computer;

import com.jpa.intermediate.aduting.Period;
import com.sun.istack.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @ToString
@Table(name = "TBL_COMPUTER")
public class Computer extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private int computerScreen;
    @NotNull private String computerBrand;
    @NotNull private String computerName;
    @NotNull private LocalDateTime computerReleaseDate;
    @Embedded @NotNull private Hardware hardware;
}
