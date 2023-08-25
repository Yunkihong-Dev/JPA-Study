package com.jpa.expert.entity.post;


import com.jpa.expert.aduting.Period;
import com.jpa.expert.entity.inquire.Answer;
import com.sun.istack.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TBL_POST")
@Getter @Setter @ToString
public class Post extends Period {
//    게시글 번호
    @Id @GeneratedValue
    @EqualsAndHashCode.Include
    private Long id;

//    게시글 제목
    @NotNull private String postTitle;

//    게시글 내용
    @NotNull private String postContent;

//    연관 객제
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST,CascadeType.REMOVE}, mappedBy ="post", orphanRemoval = true)
    private List<Like> likes = new ArrayList<>();

//    간편 메소드
    public void setLike(Like like){
        if (like.getPost() != this) {
            like.setPost(this);
        }
        this.likes.add(like);
    }
}
