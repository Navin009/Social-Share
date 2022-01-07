package com.blog.socialshare.service;

import java.util.List;

import com.blog.socialshare.model.Role;
import com.blog.socialshare.model.Users;
import com.blog.socialshare.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Users getUserById(Integer id) {
        return userRepository.findById(id).get();
    }

    public List<Users> findByName(String name) {
        return userRepository.findByNameIgnoreCaseContaining(name);
    }

    public Iterable<Users> getUsers(List<Integer> authorIds) {
        return userRepository.findAllById(authorIds);
    }

    public Users getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void registerUser(Users user) {
        Role role = new Role(0, "author");
        user.setRoles(role);
        userRepository.save(user);
    }

}
