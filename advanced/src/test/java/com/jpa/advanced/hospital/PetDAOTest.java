package com.jpa.advanced.hospital;

import com.jpa.advanced.entity.hospital.Owner;
import com.jpa.advanced.entity.hospital.Pet;
import com.jpa.advanced.repository.hospital.OwnerDAO;
import com.jpa.advanced.repository.hospital.PetDAO;
import com.jpa.advanced.type.GenderType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.Optional;

@SpringBootTest @Slf4j @Transactional @Rollback(false)
public class PetDAOTest {

    @Autowired
    private PetDAO petDAO;

    @Autowired
    private OwnerDAO ownerDAO;

//    추가
    @Test
    public void saveTest(){
        Owner owner = new Owner();
        Pet pet1 = new Pet();
        Pet pet2 = new Pet();
        final Optional<Owner> foundOwner = ownerDAO.findById(1L);
//        owner.setOwnerName("홍윤기");
//        owner.setOwnerPhone("01012341234");
//        ownerDAO.save(owner);

        pet2.setPetDisease("병");
        pet2.setGenderType(GenderType.MALE);
        pet2.setPetName("나비");
//        pet2.setOwner(foundOwner.get());
        petDAO.save(pet2);
    }

//    조회
    @Test
    public void findByIdTest(){

    }

//    전체 조회
    @Test
    public void findAllTest(){

    }

//    수정
    @Test
    public void updateTest(){

    }

//    삭제
    @Test
    public void deleteTest(){

    }
}
