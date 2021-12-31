package com.blog.socialshare.service;

import java.util.List;

import com.blog.socialshare.model.Role;
import com.blog.socialshare.model.User;
import com.blog.socialshare.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(Integer id) {
        return userRepository.findById(id).get();
    }

    public List<User> findByName(String name) {
        return userRepository.findByNameIgnoreCaseContaining(name);
    }

    public Iterable<User> getUsers(List<Integer> authorIds) {
        return userRepository.findAllById(authorIds);
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void registerUser(User user) {
        Role role = new Role(0, "author");
        user.setRoles(role);
        userRepository.save(user);
    }

}
