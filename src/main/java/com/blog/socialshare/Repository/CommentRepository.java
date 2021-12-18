package com.blog.socialshare.Repository;

import java.util.List;

import com.blog.socialshare.Model.Comment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Integer> {
    List<Comment> findByPostId(Integer postId);
}
