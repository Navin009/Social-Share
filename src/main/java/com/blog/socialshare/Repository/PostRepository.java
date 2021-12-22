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

        @Query(value = "SELECT id,title,excerpt, author, created_at FROM post where is_published = true limit 10", nativeQuery = true)
        List<Object[]> findPosts();

        @Query(value = "SELECT id,title,excerpt, author, created_at FROM post where is_published = true limit :limit offset :start ", nativeQuery = true)
        List<Object[]> findPosts(int start, int limit);

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

        @Query(value = "select * from post where title ilike '%'||:query||'%' or " +
                        " content ilike '%'||:query||'%' or " +
                        " excerpt ilike '%'||:query||'%' or " +
                        " author ilike '%'||:query||'%' ", nativeQuery = true)
        List<Post> searchPostByWord(@Param("query") String query);
}
