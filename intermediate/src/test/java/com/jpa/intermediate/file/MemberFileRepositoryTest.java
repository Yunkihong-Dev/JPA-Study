package com.jpa.intermediate.file;


import com.jpa.intermediate.repository.MemberFileRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@Transactional
@SpringBootTest @Slf4j @Rollback(false)
public class MemberFileRepositoryTest {
    @Autowired
    private MemberFileRepository memberFileRepository;

    @Test
    public void saveTest() {
        MemberFile memberFile = new MemberFile();
        memberFile.setMemberName("홍윤기");
        memberFile.setFilePath("2023/08/27");
        memberFile.setUuid(UUID.randomUUID().toString());
        memberFile.setFileName("전성기.jpg");
        memberFile.setFileSize(1896416L);
        memberFileRepository.save(memberFile);
    }

    @Test
    public void findAllTest(){
        log.info(memberFileRepository.findAll().toString());
    }

    @Test
    public void findAllByFileNamesTests(){
       assertThat(memberFileRepository.findAllByFileNames(new ArrayList<>(Arrays.asList("전성기.jpg ","현재.jpg "))))
               .hasSize(2);
    }


}
