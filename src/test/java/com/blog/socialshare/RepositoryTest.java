package com.blog.socialshare;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.blog.socialshare.Repository.PostRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@SpringBootTest
public class RepositoryTest {
    @Autowired
    PostRepository postRepository;

    @Test
    public void testFun() {
        List<Integer> inputList = new ArrayList<>(Arrays.asList(8, 9));
        Pageable pageable = PageRequest.of(0, 10);
        System.out.println(postRepository.getPostsByAuthorId(inputList, pageable).getContent());
    }

}
