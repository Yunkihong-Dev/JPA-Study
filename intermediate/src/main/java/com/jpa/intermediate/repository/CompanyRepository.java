package com.jpa.intermediate.repository;

import com.jpa.intermediate.entity.user.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    //경기도에 위치한 기업 회원의 아이디 뒤에 3을 붙이자!

//        벌크연산 : 여러 개 수정
//        벌크 연산시, 1차 캐시를 통과하지 않고 바로 쿼리가 발생된다.
//        따라서 수정 전 데이터가 1차 캐시에 등록되어 있다면 수정 후에도 1차캐시의 데이터로 작업하게 된다.
//        항상 벌크 연산 수행 시에는 cLearAutomatically=true 를 사용하여 다음 조회 때에는 수정 후의 데이터를 조회하도록 설정해야 한다.
    @Modifying(clearAutomatically = true)
    @Query("update Company c set c.userId = concat(c.userId, :number), c.updatedDate =  current_timestamp where c.address.address=:address ")
    public void updateUserIdByAddress(String  number, String address);
}
