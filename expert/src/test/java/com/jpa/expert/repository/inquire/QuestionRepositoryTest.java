package com.jpa.expert.repository.inquire;


import com.jpa.expert.entity.inquire.Answer;
import com.jpa.expert.entity.inquire.Question;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Transactional @SpringBootTest @Slf4j @Rollback(false)
public class QuestionRepositoryTest {
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;
    @Test
    public void saveTest() {
//        Question question = new Question();
//        question.setQuestionTitle("JPA 배워야 하나요?");
//        question.setQuestionContent("제발 배우고 와주세요TT");
//        questionRepository.save(question);
//
//       final Optional<Question> foundQuestion = questionRepository.findById(122L);
//
//
//        List<Answer> answers = new ArrayList<>();
//
//        Answer answer1 = new Answer();
//        answer1.setAnswerContent("네");
//        foundQuestion.ifPresent(answer1::setQuestion);
//
//        Answer answer2 = new Answer();
//        answer2.setAnswerContent("네");
//        foundQuestion.ifPresent(answer2::setQuestion);
//
//        Answer answer3 = new Answer();
//        answer3.setAnswerContent("네");
//        foundQuestion.ifPresent(answer3::setQuestion);
//
//        answers.add(answer1);
//        answers.add(answer2);
//        answers.add(answer3);
//
//        answerRepository.saveAll(answers);
//        log.info(questionRepository.findAllWithAnswerCount().toString());





    }

    @Test
    public void findAllTests (){
        List<Answer> foundAnswers = answerRepository.findAll();
    }





}
