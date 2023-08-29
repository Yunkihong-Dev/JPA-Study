package com.jpa.intermediate.file;

import com.jpa.intermediate.repository.PostFileRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional @SpringBootTest @Slf4j @Rollback(false)
public class PostFileRepositoryTest {

    @Autowired
    private PostFileRepository postFileRepository;

    @Test
    public void saveTest(){

        IntStream.range(0,5).forEach(i-> {
            PostFile postFile = new PostFile();
            postFile.setFileName("게시글사진" + i + ".jpg");
            postFile.setPostContent("게시글" + i + ".jpg");
            postFile.setUuid(UUID.randomUUID().toString());
            postFile.setFileSize(1231231L);
            postFile.setFilePath("2023/03/" + i);
            postFileRepository.save(postFile);
        });

    }

    @Test
    public void findByPostFileAndFilePathDateTests(){
        assertThat(postFileRepository.findByPostFileAndFilePathDate(new ArrayList<>(Arrays.asList("2023/03/1", "2023/03/2", "2023/03/3", "2023/03/4", "2023/03/5")))
                    ).hasSize(5);
    }
}
