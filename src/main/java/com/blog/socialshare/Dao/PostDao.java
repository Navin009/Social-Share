package com.blog.socialshare.Dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.blog.socialshare.Model.Post;
import com.blog.socialshare.Repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostDao {
    private static final int ID = 0;
    private static final int TITLE = 1;
    private static final int EXCERPT = 2;
    private static final int AUTHOR = 3;
    private static final int CREATED_AT = 4;

    @Autowired
    PostRepository postRepository;

    public List<Post> getPosts() {
        List<Object[]> posts = postRepository.findPosts();
        List<Post> postList = new ArrayList<>();

        for (Object[] post : posts) {
            Post p = new Post();
            p.setId((Integer) post[ID]);
            p.setTitle((String) post[TITLE]);
            p.setExcerpt((String) post[EXCERPT]);
            p.setAuthor((String) post[AUTHOR]);
            p.setCreatedAt((Date) post[CREATED_AT]);
            postList.add(p);
        }
        return postList;
    }

    public List<Post> getPosts(int start, int end) {
        List<Object[]> posts = postRepository.findPosts(start, end);
        List<Post> postList = new ArrayList<>();
        for (Object[] post : posts) {
            Post p = new Post();
            p.setId((Integer) post[ID]);
            p.setTitle((String) post[TITLE]);
            p.setExcerpt((String) post[EXCERPT]);
            p.setAuthor((String) post[AUTHOR]);
            p.setCreatedAt((Date) post[CREATED_AT]);
            postList.add(p);
        }
        return postList;
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
