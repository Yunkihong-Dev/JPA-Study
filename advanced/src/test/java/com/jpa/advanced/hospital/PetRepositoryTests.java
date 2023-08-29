package com.jpa.advanced.hospital;

import com.jpa.advanced.entity.hospital.Owner;
import com.jpa.advanced.entity.hospital.Pet;
import com.jpa.advanced.repository.hospital.OwnerRepository;
import com.jpa.advanced.repository.hospital.PetRepository;
import com.jpa.advanced.type.GenderType;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Rollback(false) @Slf4j
@SpringBootTest
public class PetRepositoryTests {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private OwnerRepository ownerRepository;

    @Test
    public void saveTest(){
        final Optional<Owner> foundOwner = ownerRepository.findById (118L);
        Pet pet = new Pet();

        pet.setPetName("");
        pet.setGenderType(GenderType.MALE);
        pet.setPetDisease("암");
        foundOwner.ifPresent(pet::setOwner);
        petRepository.save(pet);

    }

    @Test
    public void findAllTest(){
        log.info(petRepository.findAll().toString());
//        final List<Pet> foundPets = petRepository.findAll();
//        log.info(foundPets.get(0).getOwner().getOwnerName());
//        log.info(foundPets.get(2).getOwner().getOwnerName());

        log.info(petRepository.findAllPetDisease("간암").toString());

    }



}
