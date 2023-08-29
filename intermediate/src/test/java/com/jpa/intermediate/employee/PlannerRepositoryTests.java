package com.jpa.intermediate.employee;

import com.jpa.intermediate.entity.employee.Planner;
import com.jpa.intermediate.repository.PlannerRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.time.LocalDate;

@SpringBootTest
@Slf4j
@Rollback(false) @Transactional
public class PlannerRepositoryTests {
    @Autowired
    private PlannerRepository plannerRepository;

    @Test
    public void saveTest() {
        for(int i=0;i<50;i++) {
            Planner planner = new Planner();
            planner.setName("기획자"+(i+1));
            planner.setCareer(7);
            planner.setBirth(LocalDate.of(1980+(i%11), 1 + (i%12), 1+(i%30)));
            planner.setClientCount(i*10);
            planner.setPlannerOaLevel(1+(i%5));
            plannerRepository.save(planner);
        }
    }
    @Test
    public void findAllByPlanner0aLevelIsNotNullTest(){
        PageRequest pageRequest = PageRequest.of(1,10, Sort.Direction.DESC,"id");
        plannerRepository.findAllByPlannerOaLevelIsNotNull(pageRequest).map(Planner::toString).stream().forEach(log::info);
        final Page<Planner> pagingOfPlanner = plannerRepository.findAllByPlannerOaLevelIsNotNull(pageRequest);
        log.info("전체 페이지 개수 : {}", String.valueOf(pagingOfPlanner.getTotalPages()));
        log.info("전체 정보 개수 : {}", String.valueOf(pagingOfPlanner.getTotalElements()));
        log.info("현재 페이지 번 : {}", String.valueOf(pagingOfPlanner.getNumber()));
        log.info("페이지당 나오는 정보의 개수 : {} ", String.valueOf(pagingOfPlanner.getNumberOfElements()));
        log.info("다음 페이지 여부: {}", String.valueOf(pagingOfPlanner. hasNext ()));
        log.info("이전 페이지 여부: {}", String.valueOf(pagingOfPlanner.hasPrevious()));
        log.info("현재 페이지가 첫 페이지 인지 검사: {}", String.valueOf(pagingOfPlanner.isFirst()));
        log.info("현재 페이지가 마지막 페이지 인지 검사: {}", String.valueOf(pagingOfPlanner.isLast()));
        log.info("정보 : {}",pagingOfPlanner.getContent().toString());
    }

    @Test
    public void findTop5ByOrderByClientCountDescTest(){
        plannerRepository.findTop5ByOrderByClientCountDesc().stream().map(Planner::toString).forEach(log::info);
    }


}
