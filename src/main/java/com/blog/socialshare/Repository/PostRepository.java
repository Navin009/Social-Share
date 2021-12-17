package com.blog.socialshare.Repository;

import com.blog.socialshare.Model.Post;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<Post, Long> {
    // @Query("select p.id, p.title, p.excerpt, p.published_at , p.author from Post p join  where p.id = :id")
    // List<Object[]> findPostByWord(String word);
}
