package com.blog.socialshare.Repository;

import java.util.Date;
import java.util.List;

import com.blog.socialshare.Model.Post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

        @Query("SELECT title, author.name , excerpt, content, createdAt FROM Post where isPublished = true ")
        List<Object[]> getAllPosts();

        @Query(value = "select id, title , excerpt , author,  created_at from post where is_published = true limit :limit offset :start", nativeQuery = true)
        List<Object[]> getPosts(@Param("start") int start, @Param("limit") int limit);

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
                        "or lower(p.author.name) like lower(concat('%',:query,'%'))")
        Page<Post> searchPostsByWord(@Param("query") String query, Pageable pageable);

        Page<Post> findAll(Pageable pageable);

        @Query("select p from Post p where lower(p.title) like lower(concat('%',:query,'%')) " +
                        "or lower(p.content) like lower(concat('%',:query,'%')) " +
                        "or lower(p.excerpt) like lower(concat('%',:query,'%')) " +
                        "or lower(p.author.name) like lower(concat('%',:query,'%')) ")
        Page<Post> searchPostsByWordAndSort(@Param("query") String query, Pageable pageable);

        @Query(value = "select distinct tag.name from post " +
                        "left join post_tag on post.id = post_tag.post_id " +
                        "left join tag on post_tag.tag_id= tag.id " +
                        "where post.id= :postId", nativeQuery = true)
        List<String> getTags(@Param("postId") Integer postId);

        @Query("select p from Post p, PostTag pt, Tag t where p.id = pt.postId and "
                        + "pt.tagId = t.id and t.id in (:tagids)")
        Page<Post> getPostsByTagId(@Param("tagids") List<Integer> tagIds, Pageable pageable);

        @Query("select p from Post p, PostTag pt, Tag t where p.id = pt.postId and " +
                        "pt.tagId = t.id and p.author.id in (:authorIds)")
        Page<Post> getPostsByAuthorId(@Param("authorIds") List<Integer> authorId, Pageable pageable);

        @Query("select p from Post p, PostTag pt, Tag t " +
                        "where p.id = pt.postId and pt.tagId = t.id and " +
                        " p.author.id = (:authorIds) and t.id in (:tagIds)")
        Page<Post> getPostsByAuthorIdAndTagId(@Param("authorIds") List<Integer> authorId,
                        @Param("tagIds") List<Integer> tagId, Pageable pageable);

        @Query("select p from Post p, PostTag pt, Tag t " +
                        "where p.id = pt.postId and t.id = pt.tagId and " +
                        "lower(p.auther.name) like concat('%', :search,'%') or " +
                        "lower(p.title) like concat('%', :search,'%') or " +
                        "lower(p.content) like concat('%', :search,'%') or " +
                        "lower(p.excerpt) like concat('%', :search,'%') and " +
                        "t.id in (:tagIds)")
        Page<Post> getPostsBySearchAndTagId(@Param("search") String search,
                        @Param("tagIds") List<Integer> tagId, Pageable pageable);

        @Query("select p from Post p, PostTag pt, Tag t " +
                        "where p.id = pt.postId and t.id = pt.tagId and " +
                        "lower(p.auther.name) like concat('%', :search,'%') or " +
                        "lower(p.title) like concat('%', :search,'%') or " +
                        "lower(p.content) like concat('%', :search,'%') or " +
                        "lower(p.excerpt) like concat('%', :search,'%') and " +
                        "p.author.id in (:authorIds)")
        Page<Post> getPostsBySearchAndAuthorId(@Param("search") String search,
                        @Param("authorIds") List<Integer> authorId, Pageable pageable);

        @Query("select p from Post p, PostTag pt, Tag t " +
                        "where p.id = pt.postId and t.id = pt.tagId and " +
                        "lower(p.auther.name) like concat('%', :search,'%') or " +
                        "lower(p.title) like concat('%', :search,'%') or " +
                        "lower(p.content) like concat('%', :search,'%') or " +
                        "lower(p.excerpt) like concat('%', :search,'%') and " +
                        "p.author.id in (:authorIds) and t.id in (:tagIds)")
        Page<Post> getPostsBySearchAndAuthorIdAndTagId(@Param("search") String search,
                        @Param("authorIds") List<Integer> authorId,
                        @Param("tagIds") List<Integer> tagId, Pageable pageable);

}
