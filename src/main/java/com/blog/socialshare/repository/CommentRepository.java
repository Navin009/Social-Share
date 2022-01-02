package com.blog.socialshare.repository;

import java.util.Date;
import java.util.List;

import com.blog.socialshare.dto.CommentDTO;
import com.blog.socialshare.model.Comment;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {

    @Query("select new com.blog.socialshare.dto.CommentDTO(c.id, c.name , c.email, c.comment, c.updatedAt, c.createdAt ) from Comment c where postId.id = :post_id")
    List<CommentDTO> getCommentsByPostId(@Param("post_id") Integer postId);

    @Modifying
    @Transactional
    @Query(value = "Update comment set comment = :comment, updated_at = :updatedat  where id = :id ", nativeQuery = true)
    void updateComment(@Param("id") Integer id, @Param("comment") String comment, @Param("updatedat") Date updatedAt);
}
