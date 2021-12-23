package com.blog.socialshare;

import com.blog.socialshare.Repository.PostRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RepositoryTest {
    @Autowired
    PostRepository postRepository;

    @Test
    public void testFun() {

    }

}
