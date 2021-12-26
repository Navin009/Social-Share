package com.blog.socialshare.Service;

import java.util.List;

import com.blog.socialshare.Model.User;
import com.blog.socialshare.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findByName(String name) {
        return userRepository.findByNameIgnoreCaseContaining(name);
    }

    public Iterable<User> getUsers(List<Integer> authorIds) {
        return userRepository.findAllById(authorIds);
    }

}
