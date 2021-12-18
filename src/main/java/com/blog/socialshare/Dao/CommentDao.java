package com.blog.socialshare.Dao;

import java.util.Date;
import java.util.List;

import com.blog.socialshare.Model.Comment;
import com.blog.socialshare.Repository.CommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentDao {
    @Autowired
    CommentRepository commentRepository;

    public boolean saveComment(Integer postId, String name, String email, String commentData) {
        Comment comment = new Comment();
        comment.setPostId(postId);
        comment.setName(name);
        comment.setEmail(email);
        comment.setComment(commentData);
        comment.setCreatedAt(new Date());
        comment.setUpdatedAt(new Date());
        commentRepository.save(comment);
        return true;
    }

    public List<Comment> getCommentsByPostId(Integer postId) {
        try {
            return commentRepository.findByPostId(postId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
