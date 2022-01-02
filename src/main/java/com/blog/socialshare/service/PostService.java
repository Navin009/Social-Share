package com.blog.socialshare.service;

import java.util.Date;
import java.util.List;

import com.blog.socialshare.dto.PostSummery;
import com.blog.socialshare.model.Post;
import com.blog.socialshare.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getPostsPage(int start, int limit, Date startDate, Date endDate) {
        Pageable pageable = PageRequest.of(start / limit, limit);
        List<Post> postList = postRepository.getPosts(startDate, endDate, pageable);
        return postList;
    }

    public List<Post> getPostsBySearch(String query, Integer start, Integer limit, Date startDate, Date endDate) {
        Pageable pageable = PageRequest.of(start / limit, limit);
        Page<Post> posts = postRepository.searchPostsByWord(query, startDate, endDate, pageable);
        return posts.getContent();
    }

    public Post savePost(Post post) {
        post.setCreatedAt(new Date());
        post.setPublishedAt(new Date());
        post.setUpdatedAt(new Date());
        return postRepository.save(post);
    }

    public Post getPostById(Integer id) {
        return postRepository.findById(id).get();
    }

    public void deletePost(Integer id) {
        postRepository.deleteById(id);
    }

    public void updatePost(Post updatedPost) {
        Post post = postRepository.findById(updatedPost.getId()).get();
        post.setUpdatedAt(new Date());
        post.setTitle(updatedPost.getTitle());
        post.setExcerpt(updatedPost.getExcerpt());
        post.setContent(updatedPost.getContent());
        postRepository.save(post);
    }

    public List<Post> getPostsAndSorted(Integer start, Integer limit, String sortField,
            String order, Date startDate, Date endDate) {
        Sort sort;
        if (order.equals("asc")) {
            sort = Sort.by(sortField).ascending();
        } else {
            sort = Sort.by(sortField).descending();
        }
        Pageable pageable = PageRequest.of(start / limit, limit, sort);
        return postRepository.findAll(pageable).getContent();
    }

    public List<Post> getPostsBySearchAndSorted(String searchQuery, Integer start,
            Integer limit, String sortField, String order, Date startDate, Date endDate) {
        Sort sort;
        if (order.equals("asc")) {
            sort = Sort.by(sortField).ascending();
        } else {
            sort = Sort.by(sortField).descending();
        }
        Pageable pageable = PageRequest.of(start / limit, limit, sort);
        return postRepository.searchPostsByWordAndSort(searchQuery, startDate, endDate, pageable).getContent();
    }

    public List<String> getTagNames(Integer postId) {
        List<String> tags = postRepository.getTags(postId);
        return tags;
    }

    public List<Post> getPostsPageByTagId(List<Integer> tagIds, Integer start, Integer limit,
            Date startDate, Date endDate) {
        Pageable pageable = PageRequest.of(start / limit, limit);
        return postRepository.getPostsByTagId(tagIds, startDate, endDate, pageable).getContent();
    }

    public List<Post> getPostsPageByAuthorId(List<Integer> authorId, Integer start, Integer limit,
            Date startDate, Date endDate) {
        Pageable pageable = PageRequest.of(start / limit, limit);
        return postRepository.getPostsByAuthorId(authorId, startDate, endDate, pageable).getContent();
    }

    public List<Post> getPostsPageByAuthorIdAndTagId(List<Integer> authorId, List<Integer> tagId, Integer start,
            Integer limit, Date startDate, Date endDate) {
        Pageable pageable = PageRequest.of(start / limit, limit);
        return postRepository.getPostsByAuthorIdAndTagId(authorId, tagId, startDate, endDate, pageable).getContent();
    }

    public List<Post> getPostsBySearchAndTagId(String search, List<Integer> tagIds, Integer start, Integer limit,
            Date startDate, Date endDate) {
        Pageable pageable = PageRequest.of(start / limit, limit);
        return postRepository.getPostsBySearchAndTagId(search, tagIds, startDate, endDate, pageable).getContent();
    }

    public List<Post> getPostsBySearchAndAuthorId(String search, List<Integer> authorId, Integer start, Integer limit,
            Date startDate, Date endDate) {
        Pageable pageable = PageRequest.of(start / limit, limit);
        return postRepository.getPostsBySearchAndAuthorId(search, authorId, startDate, endDate, pageable).getContent();
    }

    public List<Post> getPostsBySearchAndAuthorIdAndTagId(String search,
            List<Integer> authorId, List<Integer> tagId, Integer start, Integer limit, Date startDate, Date endDate) {
        Pageable pageable = PageRequest.of(start / limit, limit);
        return postRepository.getPostsBySearchAndAuthorIdAndTagId(search, authorId, tagId, startDate, endDate, pageable).getContent();
    }

    public List<Post> getPostsByTagIdAndSorted(Integer start, Integer limit,
            String sortField, List<Integer> tagIds, String order, Date startDate, Date endDate) {
        Sort sort;
        if (order.equals("asc")) {
            sort = Sort.by(sortField).ascending();
        } else {
            sort = Sort.by(sortField).descending();
        }
        Pageable pageable = PageRequest.of(start / limit, limit, sort);
        return postRepository.getPostsByTagId(tagIds, startDate, endDate, pageable).getContent();
    }

    public List<Post> getPostsByAuthorIdAndSorted(Integer start, Integer limit,
            String sortField, List<Integer> authorIds, String order, Date startDate, Date endDate) {
        Sort sort;
        if (order.equals("asc")) {
            sort = Sort.by(sortField).ascending();
        } else {
            sort = Sort.by(sortField).descending();
        }
        Pageable pageable = PageRequest.of(start / limit, limit, sort);
        return postRepository.getPostsByAuthorId(authorIds, startDate, endDate, pageable).getContent();
    }

    public List<Post> getPostsByAuthorIdAndTagIdAndSorted(Integer start, Integer limit, String string,
            List<Integer> authorIds, List<Integer> tagIds, String order, Date startDate, Date endDate) {
        Sort sort;
        if (order.equals("asc")) {
            sort = Sort.by(string).ascending();
        } else {
            sort = Sort.by(string).descending();
        }
        Pageable pageable = PageRequest.of(start / limit, limit, sort);
        return postRepository.getPostsByAuthorIdAndTagId(authorIds, tagIds, startDate, endDate, pageable).getContent();
    }

    public List<Post> getPostsBySearchAndTagIdAndSorted(String searchQuery, Integer start, Integer limit, String string,
            List<Integer> tagIds, String order, Date startDate, Date endDate) {
        Sort sort;
        if (order.equals("asc")) {
            sort = Sort.by(string).ascending();
        } else {
            sort = Sort.by(string).descending();
        }
        Pageable pageable = PageRequest.of(start / limit, limit, sort);
        return postRepository.getPostsBySearchAndTagId(searchQuery, tagIds, startDate, endDate, pageable).getContent();
    }

    public List<Post> getPostsBySearchAndAuthorIdAndSorted(String searchQuery, Integer start, Integer limit,
            String string, List<Integer> authorIds, String order, Date startDate, Date endDate) {
        Sort sort;
        if (order.equals("asc")) {
            sort = Sort.by(string).ascending();
        } else {
            sort = Sort.by(string).descending();
        }
        Pageable pageable = PageRequest.of(start / limit, limit, sort);
        return postRepository.getPostsBySearchAndAuthorId(searchQuery, authorIds, startDate, endDate, pageable).getContent();
    }

    public List<Post> getPostsBySearchAndAuthorIdAndTagIdAndSorted(String searchQuery, Integer start, Integer limit,
            String string, List<Integer> authorIds, List<Integer> tagIds, String order, Date startDate, Date endDate) {
        Sort sort;
        if (order.equals("asc")) {
            sort = Sort.by(string).ascending();
        } else {
            sort = Sort.by(string).descending();
        }
        Pageable pageable = PageRequest.of(start / limit, limit, sort);
        return postRepository.getPostsBySearchAndAuthorIdAndTagId(searchQuery,
                authorIds, tagIds, startDate, endDate, pageable).getContent();
    }

    public List<PostSummery> getPostSummeries() {
        Pageable pageable = PageRequest.of(0, 10);
        return postRepository.getAllPosts(pageable);
    }

}
