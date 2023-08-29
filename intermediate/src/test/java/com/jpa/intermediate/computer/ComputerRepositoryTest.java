package com.jpa.intermediate.computer;

import com.jpa.intermediate.entity.computer.Computer;
import com.jpa.intermediate.entity.computer.Hardware;
import com.jpa.intermediate.repository.ComputerRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@SpringBootTest @Slf4j @Rollback(false) @Transactional
public class ComputerRepositoryTest {

    @Autowired
    private ComputerRepository computerRepository;

    @Test
    public void findOneComputerHardwareByIdTest(){
       computerRepository.findOneHardwareById(1L).ifPresent(hardware -> log.info(hardware.toString()));
    }
    @Test
    public void findByHardwareRamTest(){
        log.info(computerRepository.findByHardwareRam(8).get(0).toString());
    }

    @Test
    public void saveTest(){
        Hardware hardware = new Hardware();
        hardware.setRam(8);
        hardware.setSsd(1024);
        hardware.setGpu("RTX4090");
        hardware.setProcessor("i7 14700kf");
        Computer computer = new Computer();
        computer.setComputerName("조립식 컴퓨터");
        computer.setComputerPrice(1_000_000_000);
        computer.setComputerBrand("DANAWA");
        computer.setComputerReleaseDate(LocalDateTime.now());
        computer.setComputerScreen(1440);
        computer.setHardware(hardware);
        computerRepository.save(computer);
    }
}
