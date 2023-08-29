package com.jpa.advanced.entity.hospital;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
@Setter
@Table(name = "TBL_OWNER")
public class Owner {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private String ownerName;

    @Column(unique = true)
    private String ownerPhone;

//    @OneToMany (fetch = FetchType. LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
//    @JoinColumn (name = "OWNER ID")
//    private List<Pet> pets = new ArrayList<>();
}
