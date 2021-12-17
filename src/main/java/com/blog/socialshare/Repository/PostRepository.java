package com.blog.socialshare.Repository;

import java.util.Optional;

import com.blog.socialshare.Model.Post;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {
    Optional<Post> findById(Integer id);
}
