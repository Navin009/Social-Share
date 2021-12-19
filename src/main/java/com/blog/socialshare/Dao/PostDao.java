package com.blog.socialshare.Dao;

import java.util.Date;
import java.util.List;

import com.blog.socialshare.Model.Post;
import com.blog.socialshare.Repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostDao {

    @Autowired
    PostRepository postRepository;

    public List<Post> getPosts() {
        return postRepository.findPosts();
    }

    public List<Post> searchPost(String query) {
        return null;
    }

    public boolean savePost(String title, String excerpt, String content) {
        Post post = new Post();
        post.setTitle(title);
        post.setExcerpt(excerpt);
        post.setContent(content);
        post.setPublished(true);
        post.setCreatedAt(new Date());
        post.setAuthor("Navin Kumar");
        post.setPublishedAt("hold");
        post.setUpdatedAt(new Date());
        postRepository.save(post);
        return false;
    }

    public Post getPostById(Integer id) {
        if (postRepository.findById(id).isPresent()) {
            return postRepository.findById(id).get();
        }
        return null;
    }
}
