package com.jpa.expert.entity.inquire.domain;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class QuestionDTO {
    private Long id;
    private String questionTitle;
    private String questionContent;
    private int answerCount;
}