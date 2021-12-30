package com.blog.socialshare;

import java.util.List;

import com.blog.socialshare.model.Role;
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
        Role user = new Role(0, "author");
        Role admin = new Role(1, "admin");
        roleRepository.saveAll(List.of(user, admin));
    }

}
