package com.blog.socialshare.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.blog.socialshare.Model.Post;
import com.blog.socialshare.Repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
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

    public List<Post> getPosts(int start, int limit) {
        List<Object[]> posts = postRepository.findPosts(start, limit);
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

    public boolean savePost(Post post) {
        post.setCreatedAt(new Date());
        post.setPublishedAt(new Date());
        post.setUpdatedAt(new Date());
        Post savedPost = postRepository.save(post);
        System.out.println(savedPost.getId());
        return false;
    }

    public Post getPostById(Integer id) {

        if (postRepository.findById(id).isPresent()) {
            return postRepository.findById(id).get();
        }
        return null;
    }

    public boolean deletePost(Integer id) {

        if (postRepository.findById(id).isPresent()) {
            postRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean updatePost(Post post) {
        post.setUpdatedAt(new Date());
        postRepository.updatePost(post.getId(),
                post.getTitle(),
                post.getExcerpt(),
                post.getContent(),
                post.getAuthor(),
                post.isPublished(),
                post.getUpdatedAt());
        return true;

    }
}
