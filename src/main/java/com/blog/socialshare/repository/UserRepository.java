package com.blog.socialshare.repository;

import java.util.List;

import com.blog.socialshare.model.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    List<User> findByNameIgnoreCaseContaining(String name);
}
