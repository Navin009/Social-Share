package com.blog.socialshare.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.blog.socialshare.Model.Post;
import com.blog.socialshare.Model.User;
import com.blog.socialshare.Repository.PostRepository;
import com.blog.socialshare.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Autowired
    private UserRepository userRepository;

    public List<Post> getPosts() {
        List<Object[]> posts = postRepository.getAllPosts();
        List<Post> postList = new ArrayList<>();
        for (Object[] post : posts) {
            Post p = new Post();
            p.setId((int) post[ID]);
            p.setTitle((String) post[TITLE]);
            p.setExcerpt((String) post[EXCERPT]);
            User user = userRepository.findById((Integer) post[AUTHOR]).get();
            p.setAuthor(user);
            p.setCreatedAt((Date) post[CREATED_AT]);
            postList.add(p);
        }
        return postList;
    }

    public List<Post> getPosts(int start, int limit) {
        List<Object[]> posts = postRepository.getPosts(start, limit);
        List<Post> postList = new ArrayList<>();
        for (Object[] post : posts) {
            Post p = new Post();
            p.setId((int) post[ID]);
            p.setTitle((String) post[TITLE]);
            p.setExcerpt((String) post[EXCERPT]);

            User user = userRepository.findById((Integer) post[AUTHOR]).get();
            p.setAuthor(user);
            p.setCreatedAt((Date) post[CREATED_AT]);
            postList.add(p);
        }
        return postList;
    }

    public List<Post> getPostsBySearch(String query, Integer start, Integer limit) {
        Pageable pageable = PageRequest.of(start / limit, limit);
        return postRepository.searchPostsByWord(query, pageable).getContent();
    }

    public Post savePost(Post post) {
        post.setCreatedAt(new Date());
        post.setPublishedAt(new Date());
        post.setUpdatedAt(new Date());
        return postRepository.save(post);
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
        // postRepository.updatePost(post.getId(),
        // post.getTitle(),
        // post.getExcerpt(),
        // post.getContent(),
        // post.getAuthor(),
        // post.isPublished(),
        // post.getUpdatedAt());
        return true;

    }

    public List<Post> getPostsAndSorted(Integer start, Integer limit, String sortField, String order) {
        Sort sort;
        if (order.equals("asc")) {
            sort = Sort.by(sortField).ascending();
        } else {
            sort = Sort.by(sortField).descending();
        }
        Pageable pageable = PageRequest.of(start / limit, limit, sort);
        return postRepository.findAll(pageable).getContent();
    }

    public List<Post> getPostsBySearchAndSorted(
            String searchQuery, Integer start, Integer limit, String sortField, String order) {
        Sort sort;
        if (order.equals("asc")) {
            sort = Sort.by(sortField).ascending();
        } else {
            sort = Sort.by(sortField).descending();
        }
        Pageable pageable = PageRequest.of(start / limit, limit, sort);
        return postRepository.searchPostsByWordAndSort(searchQuery, pageable).getContent();
    }

    public List<String> getTags(Integer postId) {
        List<String> tags = postRepository.getTags(postId);
        return tags;
    }

}
