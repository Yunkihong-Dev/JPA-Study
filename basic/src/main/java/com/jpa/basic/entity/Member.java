package com.jpa.basic.entity;

import com.jpa.basic.type.MemberType;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter @ToString @Setter
@Table(name = "TBL_MEMBER")
public class Member {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull
    private String memberName;
    @Column(unique = true, nullable = false)
    private String memberEmail;
    private String memberPassword;
    private int memberAge;
    @Enumerated(value = EnumType.STRING)
    private MemberType memberType;
    private LocalDateTime generatedDate;
}
