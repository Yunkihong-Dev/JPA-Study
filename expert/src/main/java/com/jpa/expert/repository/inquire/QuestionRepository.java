package com.jpa.expert.repository.inquire;

import com.jpa.expert.entity.inquire.Question;
import com.jpa.expert.entity.inquire.domain.QuestionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {

//    attributePaths 에는 존재하는 필드명으로 작성해야 한다.
//    일대다관계에서는 join 사용 시, List 연관객제에 자동으로 매핑되지 않는다.
//    LAZY 때문에, List 연관객제를 불러올 때 쿼리가 한 번 더 발생하게 된다.이

//    q와 q. answers로 join이 실행되지만 List로 매핑 안됨
//    q에 q.answers 가 3개라면, 중복된 q가 3개 나옴
//    @Query ("select q from Question q inner join q.answers")

//    q와 q.answers로 join 실행되지만 List로 매핑 안됨
//    q에 q.answers가 3개라면, 중복된 q가 3개 나옴
//    @Query ("select q, q.answers from Question q")

//    q와 q.answer로 join이 실행되지만 List로 매핑안됨
//    q에 q. answers 가 3개라면, distinct를 통해 중복이 제거되어 q는 1개 나옴
//    @Query ("select distinct q from Question q inner join q.answers")

//    q와 q.answers로 join되지만 List로 매핑안됨
//    q에 9. answers 가 3개라면, distinct를 사용했지만, 중복이 제거되지 않고 q는 3개 나옴
//    @Query ("select distinct a, a.answers from Question q")

//    q와 q.answers로 join이 실행되지만 List로 매핑 안됨
//   q에 q.answers가 3개라면, distinct를 통해 중복이 제거되어 q는 1개 나옴
//    @Query ("select distinct q from Question q left outer join g.answers")
//    @Query("select q, size(q.answers) from Question q")
//    fetch joine Entityoraph와 마찬가지로 N + 1 문제를 해결해준다.
//    외부 조인을 사용하여 전체 조회만 한다면, Entityoraph를 사용하고, 내부 또는 외부 조인을 사용하여 전체 조회 및 조건 등 복잡한 쿼리가 필요하다면 직접 join fetch를 사용한다.
//    @Query ("select distinct q from Question q left outer join fetch q.answers")
    @Query("select distinct q from Question q left outer join fetch q.answers")


    public List<Question> findAll ();

    @Query("select new com.jpa.expert.entity.inquire.domain.QuestionDTO(q.id, q.questionTitle, q.questionContent, size(q.answers)) from Question q")
    public Page<QuestionDTO> findAllWithAnswerCount(Pageable pageable);
}
