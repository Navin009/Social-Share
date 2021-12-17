package com.blog.socialshare.Repository;

import java.util.List;

import com.blog.socialshare.Model.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Test
    public void testFindByName() {
        try {
            List<User> user = userRepository.findByName("test");
            System.out.println(((User) user).getName());
        } catch (Exception e) {
            System.out.println("User not found");
        }
    }
}
