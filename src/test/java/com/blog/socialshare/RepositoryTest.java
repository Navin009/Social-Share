package com.blog.socialshare;

import com.blog.socialshare.repository.PostRepository;
import com.blog.socialshare.repository.RoleRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RepositoryTest {
    @Autowired
    PostRepository postRepository;

    @Autowired
    RoleRepository roleRepository;

    @Test
    public void testFun() {
    }

}
