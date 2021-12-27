package com.blog.socialshare.service;

import java.util.Date;
import java.util.List;

import com.blog.socialshare.model.Comment;
import com.blog.socialshare.model.Post;
import com.blog.socialshare.repository.CommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    public boolean saveComment(Integer postId, String name, String email, String commentData) {
        Comment comment = new Comment();
        Post post = new Post();
        post.setId(postId);
        comment.setPostId(post);
        comment.setName(name);
        comment.setEmail(email);
        comment.setComment(commentData);
        comment.setCreatedAt(new Date());
        comment.setUpdatedAt(new Date());

        commentRepository.save(comment);
        return true;
    }

    public List<Comment> getCommentsByPostId(Post postId) {
        try {
            return commentRepository.getCommentsByPostId(postId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateComment(Integer commentId, String comment) {
        commentRepository.updateComment(commentId, comment, new Date());
    }

    public void deleteComment(Integer commentId) {
        commentRepository.deleteById(commentId);
    }
}
