package com.jpa.intermediate.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Setter
@Table(name = "TBL_CART")
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    private Long count;

    @ManyToOne
    private Product product;
    @ManyToOne
    private Member member;
}
