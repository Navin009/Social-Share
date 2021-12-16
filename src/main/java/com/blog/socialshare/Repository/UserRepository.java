package com.blog.socialshare.Repository;

import com.blog.socialshare.Model.User;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    public User findByName(String name);
}
