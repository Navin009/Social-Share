package com.blog.socialshare.Repository;

import java.util.List;
import java.util.Optional;

import com.blog.socialshare.Model.Post;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {

    Optional<Post> findById(Integer id);

    @Query(value = "SELECT id,title,excerpt, author, created_at FROM post limit 10", nativeQuery = true)
    List<Object[]> findPosts();

    @Query(value = "SELECT id,title,excerpt, author, created_at FROM post limit :limit offset :start ", nativeQuery = true)
    List<Object[]> findPosts(int start, int limit);
}
