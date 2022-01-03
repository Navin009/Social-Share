package com.blog.socialshare.repository;

import java.util.Date;
import java.util.List;

import com.blog.socialshare.dto.PostDTO;
import com.blog.socialshare.dto.PostSummery;
import com.blog.socialshare.model.Post;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

        @Query("select new com.blog.socialshare.dto.PostDTO(p.id, p.title, " +
                        "p.excerpt , p.content , p.author.id, p.author.name, " +
                        "p.author.email, p.isPublished) from Post p where p.id = :id ")
        PostDTO findPostDTOById(@Param("id") Integer id);

        @Query("select new com.blog.socialshare.dto.PostSummery(p.id, p.title, p.excerpt , p.publishedAt , p.author.id, p.author.name, p.author.email) from Post p where p.isPublished = true and (p.publishedAt between :startDate and :endDate) ")
        List<PostSummery> getPosts(@Param("startDate") Date startDate, @Param("endDate") Date endDate,
                        Pageable pageable);

        @Query("select new com.blog.socialshare.dto.PostSummery(p.id, " +
                        "p.title, p.excerpt , p.publishedAt , p.author.id, p.author.name, " +
                        "p.author.email) from Post p where lower(p.title) like lower(concat('%',:query,'%')) " +
                        "or lower(p.content) like lower(concat('%',:query,'%')) " +
                        "or lower(p.excerpt) like lower(concat('%',:query,'%')) " +
                        "or lower(p.author.name) like lower(concat('%',:query,'%')) and " +
                        "(p.publishedAt between :startDate and :endDate)")
        Page<PostSummery> searchPostsByWord(@Param("query") String query, @Param("startDate") Date startDate,
                        @Param("endDate") Date endDate, Pageable pageable);

        @Query("select new com.blog.socialshare.dto.PostSummery(p.id, p.title, p.excerpt , p.publishedAt , p.author.id, p.author.name, p.author.email) from Post p ")
        Page<PostSummery> findAllPosts(Pageable pageable);

        @Query("select new com.blog.socialshare.dto.PostSummery(p.id, p.title, p.excerpt , p.publishedAt , p.author.id, p.author.name, p.author.email) from Post p where lower(p.title) like lower(concat('%',:query,'%')) " +
                        "or lower(p.content) like lower(concat('%',:query,'%')) " +
                        "or lower(p.excerpt) like lower(concat('%',:query,'%')) " +
                        "or lower(p.author.name) like lower(concat('%',:query,'%')) and " +
                        "(p.publishedAt between :startDate and :endDate) ")
        Page<PostSummery> searchPostsByWordAndSort(@Param("query") String query, @Param("startDate") Date startDate,
                        @Param("endDate") Date endDate, Pageable pageable);

        @Query(value = "select distinct tag.name from post " +
                        "left join post_tag on post.id = post_tag.post_id " +
                        "left join tag on post_tag.tag_id= tag.id " +
                        "where post.id= :postId", nativeQuery = true)
        List<String> getTags(@Param("postId") Integer postId);

        @Query("select new com.blog.socialshare.dto.PostSummery(p.id, p.title, p.excerpt , p.publishedAt , p.author.id, p.author.name, p.author.email) from Post p, PostTag pt, Tag t where p.id = pt.postId and "
                        + "pt.tagId = t.id and (p.publishedAt between :startDate and :endDate) and t.id in (:tagids) group by p.id, p.author.name")
        Page<PostSummery> getPostsByTagId(@Param("tagids") List<Integer> tagIds,
                        @Param("startDate") Date startDate, @Param("endDate") Date endDate, Pageable pageable);

        @Query("select new com.blog.socialshare.dto.PostSummery(p.id, p.title, p.excerpt , p.publishedAt , p.author.id, p.author.name, p.author.email) from Post p, PostTag pt, Tag t where p.id = pt.postId and " +
                        "(p.publishedAt between :startDate and :endDate) and " +
                        "pt.tagId = t.id and p.author.id in (:authorIds) group by p.id, p.author.name")
        Page<PostSummery> getPostsByAuthorId(@Param("authorIds") List<Integer> authorId,
                        @Param("startDate") Date startDate,
                        @Param("endDate") Date endDate, Pageable pageable);

        @Query("select new com.blog.socialshare.dto.PostSummery(p.id, p.title, p.excerpt , p.publishedAt , p.author.id, p.author.name, p.author.email) from Post p, PostTag pt, Tag t " +
                        "where p.id = pt.postId and pt.tagId = t.id and " +
                        " (p.publishedAt between :startDate and :endDate) and " +
                        " p.author.id = (:authorIds) and t.id in (:tagIds) group by p.id, p.author.name")
        Page<PostSummery> getPostsByAuthorIdAndTagId(@Param("authorIds") List<Integer> authorId,
                        @Param("tagIds") List<Integer> tagId,
                        @Param("startDate") Date startDate, @Param("endDate") Date endDate,
                        Pageable pageable);

        @Query("select new com.blog.socialshare.dto.PostSummery(p.id, p.title, p.excerpt , p.publishedAt , p.author.id, p.author.name, p.author.email) from Post p, PostTag pt, Tag t " +
                        "where p.id = pt.postId and t.id = pt.tagId and " +
                        "(lower(p.author.name) like lower(concat('%', :search,'%')) or " +
                        "lower(p.title) like lower(concat('%', :search,'%')) or " +
                        "lower(p.content) like lower(concat('%', :search,'%')) or " +
                        "lower(p.excerpt) like lower(concat('%', :search,'%')) ) and " +
                        "(p.publishedAt between :startDate and :endDate) and " +
                        "t.id in (:tagIds) group by p.id, p.author.id")
        Page<PostSummery> getPostsBySearchAndTagId(@Param("search") String search,
                        @Param("tagIds") List<Integer> tagId, @Param("startDate") Date startDate,
                        @Param("endDate") Date endDate, Pageable pageable);

        @Query("select new com.blog.socialshare.dto.PostSummery(p.id, p.title, p.excerpt , p.publishedAt , p.author.id, p.author.name, p.author.email) from Post p, PostTag pt, Tag t " +
                        "where p.id = pt.postId and t.id = pt.tagId and " +
                        "(lower(p.author.name) like lower(concat('%', :search,'%')) or " +
                        "lower(p.title) like lower(concat('%', :search,'%')) or " +
                        "lower(p.content) like lower(concat('%', :search,'%')) or " +
                        "lower(p.excerpt) like lower(concat('%', :search,'%')) ) and " +
                        "(p.publishedAt between :startDate and :endDate) and " +
                        "p.author.id in (:authorIds) group by p.id, p.author.id")
        Page<PostSummery> getPostsBySearchAndAuthorId(@Param("search") String search,
                        @Param("authorIds") List<Integer> authorId, @Param("startDate") Date startDate,
                        @Param("endDate") Date endDate, Pageable pageable);

        @Query("select new com.blog.socialshare.dto.PostSummery(p.id, p.title, p.excerpt , p.publishedAt , p.author.id, p.author.name, p.author.email) from Post p, PostTag pt, Tag t " +
                        "where p.id = pt.postId and t.id = pt.tagId and " +
                        "(lower(p.author.name) like lower(concat('%', :search,'%')) or " +
                        "lower(p.title) like lower(concat('%', :search,'%')) or " +
                        "lower(p.content) like lower(concat('%', :search,'%')) or " +
                        "lower(p.excerpt) like lower(concat('%', :search,'%')) ) and " +
                        "(p.publishedAt between :startDate and :endDate) and " +
                        "p.author.id in (:authorIds) and t.id in (:tagIds) group by p.id, p.author.id")
        Page<PostSummery> getPostsBySearchAndAuthorIdAndTagId(@Param("search") String search,
                        @Param("authorIds") List<Integer> authorId,
                        @Param("tagIds") List<Integer> tagId, @Param("startDate") Date startDate,
                        @Param("endDate") Date endDate, Pageable pageable);

}
