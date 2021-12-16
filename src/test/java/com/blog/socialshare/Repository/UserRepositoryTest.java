package com.blog.socialshare.Repository;

import com.blog.socialshare.Model.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void testFindByName() {
        User user = userRepository.findByName("test");
        try {
            System.out.println(user.getName());
        } catch (Exception e) {
            System.out.println("User not found");
        }
    }
}
