package com.jpa.expert.entity.inquire;

import com.jpa.expert.repository.inquire.AnswerDAO;
import com.jpa.expert.repository.inquire.QuestionDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest @Slf4j
@Rollback(false) @Transactional
public class InquireTests {
    @Autowired
    private QuestionDAO questionDAO;

    @Autowired
    private AnswerDAO answerDAO;

    @Test
    public void saveTest(){
//        1. Question(1) -> Answer(N) 만연관관계 주인은 아니나, 목적에 맞는 로직
//        Question question = new Question();
//        question.setQuestionTitle("라면은 어떻게 끓이나요?");
//        question.setQuestionContent("제곧내");
//        questionDAO.save(question);


//        final Optional<Question> foundQuestion = questionDAO.findById(3L);
//        Answer answer1 = new Answer();
//        Answer answer2 = new Answer();
//
//        answer1.setAnswerContent("잘");
//        answer2.setAnswerContent("성실한 답변 감사합니다!");
//        foundQuestion.ifPresent(question -> {
//            answer1.setQuestion(question);
//            answer2.setQuestion(question);
//            question.getAnswers().add(answer1);
//            question.getAnswers().add(answer2);
//        });

//            2. Answer (N) -> Ovestion(1)  만연관관계 주인이지만, 목적에 맞지 않는 로직
//        Answer answer = answerDAO.findById(19L).get();
//        Question question = new Question();
//        question.setQuestionTitle("첫 데이트 어디가 좋을까요?"):
//        question.setQuestionContent(" 빨리 알려주세요! ");
//        questionDAO.save(question);
//        answer.setQuestion(question);


//        연관관계의 주인이 아닌 question.answers는 조회 목적으로만 사용한다.
//        질문에 답변을 추가하고 싶다면, answer.setquestion()를 사용하여 직접 질문을 지정해야 한다.
//        만약 매번 setouestion()을사용하기 불편하다며. 편의 메소드를 제작한다.

//         3. Question(1) -> Answer(N) 만연관관계 주인은 아니나, 목적에 맞는 로직, 편의 메소드 사

        final Optional<Question> foundQuestion = questionDAO.findById(3L);
        Answer answer = new Answer();
        answer.setAnswerContent("잘하면 됨.");
        foundQuestion.ifPresent(question -> question.setAnswer(answer));

    }

    @Test
    public void findByIdTest(){
        final Optional<Question> foundQuestion = questionDAO.findById(3L);
        foundQuestion.ifPresent(question -> question.getAnswers().forEach(answer -> log.info(answer.getAnswerContent())));
    }

    @Test
    public void findAllTest(){
        assertThat(questionDAO.findAll()).hasSize(1);
    }

    @Test
    public void updateTest(){
        Question question =questionDAO.findById(3L).get();
        question.getAnswers().get(1).setAnswerContent("수정된 답변");
    }

    @Test
    public void deleteTest() {
        Question question = questionDAO.findById(3L).get();
        question.getAnswers().remove(0);
    }
}
