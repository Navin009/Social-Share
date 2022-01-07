package com.blog.socialshare.repository;

import java.util.List;

import com.blog.socialshare.model.Users;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<Users, Integer> {

    List<Users> findByNameIgnoreCaseContaining(String name);

    Users findByEmail(String email);
}
