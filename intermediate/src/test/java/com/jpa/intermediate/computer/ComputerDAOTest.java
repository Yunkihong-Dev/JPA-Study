package com.jpa.intermediate.computer;

import com.jpa.intermediate.entity.computer.Computer;
import com.jpa.intermediate.entity.computer.Hardware;
import com.jpa.intermediate.repository.ComputerDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j @SpringBootTest
@Transactional
@Rollback(false)
public class ComputerDAOTest {

    @Autowired
    private ComputerDAO computerDAO;

    @Test
    public void saveTest(){
        Hardware hardware = new Hardware();
        Computer computer = new Computer();
        hardware.setRam(8);
        hardware.setSsd(512);
        hardware.setGpu("RTX4050");
        hardware.setProcessor("i5-13400F");
        computer.setComputerBrand("APPLE");
        computer.setComputerName("MACBOOKPRO");
        computer.setComputerPrice(6_000_000);
        computer.setComputerReleaseDate(LocalDateTime.now());
        computer.setComputerScreen(3540);
        computer.setHardware(hardware);
        computerDAO.save(computer);
    }
    @Test
    public void findByIdTest(){
        computerDAO.findById(1L).ifPresentOrElse(
                computer->{
                   assertThat(computer.getHardware().getRam()).isEqualTo(16);
                },
                ()->{
                 log.info("없는 컴퓨터입니다.");
                }
        );
    }

    @Test
    public void findAllTest(){
        assertThat(computerDAO.findAll()).hasSize(2);
    }
    @Test
    public void updateTest() {
//        ram 8GB 조회 후 가격을 30만원 인하
//        computerDAO.findAll().stream().filter(computer -> computer.getHardware().getRam() == 8)
                computerDAO.findByRam(8)
                .forEach(computer -> computer.setComputerPrice(computer.getComputerPrice()-300_000));
    }
    @Test
    public void deleteTest(){
        computerDAO.findByRam(8).forEach(computerDAO::delete);
    }
}
