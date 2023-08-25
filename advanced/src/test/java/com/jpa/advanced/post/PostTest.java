package com.jpa.advanced.post;


import com.jpa.advanced.entity.post.Post;
import com.jpa.advanced.entity.post.Reply;
import com.jpa.advanced.repository.post.PostDAO;
import com.jpa.advanced.repository.post.ReplyDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;

@SpringBootTest @Slf4j @Rollback(false) @Transactional
public class PostTest {

    @Autowired
    private PostDAO postDAO;

    @Autowired
    private ReplyDAO replyDAO;

    @Test
    public void saveTest(){
        Post post = new Post();
        Reply reply1 = new Reply();
        Reply reply2 = new Reply();

        reply1.setReplyContents("테스트 덧글 1");
        reply2.setReplyContents("테스트 덧글 2");

        replyDAO.save(reply1);
        replyDAO.save(reply2);

        post.setPostTitle("테스트 제목 1");
        post.setPostContent("테스트 내용 1");
//        post.getReplies().add(reply1);
//        post.getReplies().add(reply2);
        postDAO.save(post);
    }

    @Test
    public void findByIdTest(){
//        postDAO.findById(3L).ifPresent(post -> log.info(post.toString()));
//        replyDAO.findById(1L).ifPresent(reply -> log.info(reply.getReplyContents()));
    }

    @Test
    public void findAllTest(){
//       postDAO.findAll().stream().map(Post::getReplies).forEach(replies -> replies.forEach(reply -> log.info(reply.getReplyContents())));
    }

    @Test
    public void updateTest(){
//        replyDAO.findById(1L).ifPresent(reply -> reply.setReplyContents("수된 내용"));
//        replyDAO.findById(4L).ifPresent(reply -> reply.getPost().setPostTitle("수정된 제"));
    }

    @Test
    public void deleteTest(){
        postDAO.findById(6L).ifPresent(post -> post.getReplies().forEach(replyDAO::delete));
    }
}
