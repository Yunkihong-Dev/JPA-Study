package com.jpa.expert.entity.post;

import com.jpa.expert.aduting.Period;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity @Getter @Setter @ToString(exclude = "post")
@Table(name = "TBL_LIKE")
public class Like extends Period {
//    좋아요 번호
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
//    연관 객체
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;
}
