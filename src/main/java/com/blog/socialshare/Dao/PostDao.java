package com.blog.socialshare.Dao;

import java.util.List;
import java.util.Optional;

import com.blog.socialshare.Model.Post;
import com.blog.socialshare.Repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostDao {

    @Autowired
    PostRepository postRepository;

    public List<Post> searchPost(String query) {
        return null;
    }

    public boolean savePost(String title, String excerpt, String content) {
        Post post = new Post();
        post.setTitle(title);
        post.setExcerpt(excerpt);
        post.setContent(content);
        post.setPublished(true);
        postRepository.save(post);
        return false;
    }

    public Optional<Post> getPostById(Integer id) {
        return postRepository.findById(id);
    }

    public Post findById(Integer postId) {
        return null;
    }
}
