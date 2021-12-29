package com.blog.socialshare.repository;

import com.blog.socialshare.model.Post;
import com.blog.socialshare.model.PostTag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface PostTagRepository extends JpaRepository<PostTag, Integer> {

    @Modifying
    @Transactional
    @Query("delete from PostTag where postId = :postId")
    void deleteAllByPostId(@Param("postId") Post post);

}
