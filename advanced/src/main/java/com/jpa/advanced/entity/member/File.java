package com.jpa.advanced.entity.member;

import com.jpa.advanced.aduting.Period;
import com.sun.istack.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name="TBL_FILE")
@Getter @Setter @ToString
public class File extends Period {
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String fileName;
    @NotNull private String filePath;
    @NotNull private String fileUuid;
    @NotNull private Long fileSize;

//    1:1 관계에서는 추후에 유지보수시 N이 될 가능성이 있는 객체를 연관관계의 주인으로 설정한다.
    @OneToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "memberId")
    private Member member;
}
