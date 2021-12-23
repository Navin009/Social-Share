package com.blog.socialshare.Repository;

import java.util.Date;
import java.util.List;

import com.blog.socialshare.Model.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

        @Query("SELECT title, author.name , excerpt, content, createdAt FROM Post where isPublished = true ")
        List<Object[]> getPosts();

        @Modifying
        @Transactional
        @Query(value = "UPDATE post SET is_published = :ispublished," +
                        "title = :title," +
                        "excerpt = :excerpt," +
                        "author = :author," +
                        "updated_at = :updated_at," +
                        "content = :content " +
                        " WHERE id = :id", nativeQuery = true)
        void updatePost(@Param("id") int id,
                        @Param("title") String title,
                        @Param("excerpt") String excerpt,
                        @Param("content") String content,
                        @Param("author") String author,
                        @Param("ispublished") boolean ispublished,
                        @Param("updated_at") Date updated_at);

        @Query("select p from Post p where lower(p.title) like lower(concat('%',:query,'%')) " +
                        "or lower(p.content) like lower(concat('%',:query,'%')) " +
                        "or lower(p.excerpt) like lower(concat('%',:query,'%')) " +
                        "or lower(p.author) like lower(concat('%',:query,'%')) ")
        List<Post> searchPostsByWord(@Param("query") String query);

}
