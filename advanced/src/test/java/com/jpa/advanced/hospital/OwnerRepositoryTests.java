package com.jpa.advanced.hospital;


import com.jpa.advanced.entity.hospital.Owner;
import com.jpa.advanced.entity.hospital.Pet;
import com.jpa.advanced.repository.hospital.OwnerRepository;
import com.jpa.advanced.repository.hospital.PetRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;

@Transactional @Rollback(false) @Slf4j @SpringBootTest
public class OwnerRepositoryTests {

    @Autowired
    private OwnerRepository ownerRepository;
    @Test
    public void saveTest(){
        Owner owner = new Owner();
        owner.setOwnerName("홍윤기");
        owner.setOwnerPhone("01012321311");

        ownerRepository.save(owner);
    }


}
