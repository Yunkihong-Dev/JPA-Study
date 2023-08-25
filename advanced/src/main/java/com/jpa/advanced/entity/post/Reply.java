package com.jpa.advanced.entity.post;

import com.jpa.advanced.aduting.Period;
import com.sun.istack.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter @ToString @Setter
@Table(name = "TBL_REPLY")
public class Reply extends Period {
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;
    @NotNull private String replyContents;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "POST_ID")
//    private Post post;
}
