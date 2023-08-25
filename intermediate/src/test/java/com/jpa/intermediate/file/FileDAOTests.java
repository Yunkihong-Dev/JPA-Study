package com.jpa.intermediate.file;

import com.jpa.intermediate.repository.MemberFileDAO;
import com.jpa.intermediate.repository.PostFileDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.UUID;

@SpringBootTest
@Slf4j
@Transactional
@Rollback(false)
public class FileDAOTests {
    @Autowired
    private MemberFileDAO memberFileDAO;

    @Test
    public void saveTest() {
        MemberFile memberFile = new MemberFile();
        memberFile.setMemberName("H");
        memberFile.setFileName("J7|.png");
        memberFile.setFilePath("2023/06/18");
        memberFile.setUuid(UUID.randomUUID().toString());
        memberFile.setFileSize(15896496L);
        memberFileDAO.save(memberFile);
    }

    @Test
    public void findByIdTest(){
        memberFileDAO.findById(1L).ifPresentOrElse(memberFile->{log.info(memberFile.toString());},()->{
            log.info("없는 친구");
        });
    }
    @Test
    public void findAll(){
        memberFileDAO.findAll().stream().map(MemberFile::toString).forEach(log::info);
    }
    @Test
    public void updateTest(){
        memberFileDAO.findById(2L).ifPresent(memberFile -> memberFile.setMemberName("홍윤기2"));
    }

    @Autowired
    private PostFileDAO postFileDAO;

    @Test
    public void savePostTest(){
        PostFile postFile = new PostFile();
        postFile.setFileName("게시글사진");
        postFile.setFilePath("2020/01/30");
        postFile.setFileSize(22123L);
        postFile.setPostContent("테스트용 내용");
        postFile.setUuid(UUID.randomUUID().toString());
        postFileDAO.save(postFile);
    }

    @Test
    public void findByIdPostTest(){
        postFileDAO.findById(3L).ifPresentOrElse(postFile -> postFile.setPostContent("바뀐 게시글 내용"),()->{
            log.info("없어용");
        });
    }
}