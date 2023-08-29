package com.jpa.advanced.entity.hospital;

import com.jpa.advanced.type.GenderType;
import com.sun.istack.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Setter
@Table(name = "TBL_PET")
public class Pet {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String petName;
    @Enumerated(EnumType.STRING)
    @NotNull private GenderType genderType;
    @NotNull private String petDisease;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "OWNER_ID")
    private Owner owner;
}
