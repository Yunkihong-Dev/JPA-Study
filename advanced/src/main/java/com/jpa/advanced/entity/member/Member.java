package com.jpa.advanced.entity.member;


import com.jpa.advanced.aduting.Period;
import com.sun.istack.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@ToString @Getter @Setter @Entity
@Table(name = "MEMBER")
public class Member extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String memberId;
    @NotNull private String memberPassword;
    @NotNull private String memberEmail;
    @Embedded private MemberAddress memberAddress;
}
