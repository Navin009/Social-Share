package com.blog.socialshare.Service;

import java.util.Date;
import java.util.List;

import com.blog.socialshare.Model.Comment;
import com.blog.socialshare.Model.Post;
import com.blog.socialshare.Repository.CommentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    @Autowired
    CommentRepository commentRepository;

    public boolean saveComment(Integer postId, String name, String email, String commentData) {
        Comment comment = new Comment();

        // comment.setPostId(postId);
        // comment.setName(name);
        // comment.setEmail(email);
        // comment.setComment(commentData);
        // comment.setCreatedAt(new Date());
        // comment.setUpdatedAt(new Date());

        // commentRepository.save(comment);
        return true;
    }

    public List<Comment[]> getCommentsByPostId(Post postId) {
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
