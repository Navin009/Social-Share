package com.blog.socialshare.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.blog.socialshare.dto.CommentDTO;
import com.blog.socialshare.dto.PostDTO;
import com.blog.socialshare.dto.TagDTO;
import com.blog.socialshare.model.Post;
import com.blog.socialshare.model.Tag;
import com.blog.socialshare.model.User;
import com.blog.socialshare.service.CommentService;
import com.blog.socialshare.service.PostService;
import com.blog.socialshare.service.PostTagService;
import com.blog.socialshare.service.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@RestController
@RequestMapping("api")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private TagService tagService;

    @Autowired
    private PostTagService postTagService;

    @Autowired
    private CommentService commentService;

    @RequestMapping(path = "/blog")
    public ResponseEntity<String> blogPage() {
        return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY).header(HttpHeaders.LOCATION, "/").build();
    }

    @GetMapping("/blog/{postid}")
    public PostDTO getPostById(@PathVariable("postid") Integer postId, Model model) {
        PostDTO post = postService.getPostById(postId);
        List<CommentDTO> comments = commentService.getCommentsByPostId(postId);
        List<TagDTO> tags = tagService.getTagsByPostId(postId);
        post.setComments(comments);
        post.setTags(tags);
        return post;
    }

    @DeleteMapping("/blog/delete/{postid}")
    public ResponseEntity<String> deletePost(@PathVariable("postid") Integer postId,
            @SessionAttribute("loggedUser") User user) {
        postService.deletePost(postId, user);
        return ResponseEntity.ok("Post deleted successfully");
    }

    @PostMapping(path = "/newpost/save")
    public ResponseEntity<String> savePost(@ModelAttribute("post") Post post, @SessionAttribute("loggedUser") User user,
            @RequestParam("tagsData") String tagsList, Model model) {
        post.setAuthor(user);
        Post savedPost = postService.savePost(post);
        List<String> tagTokens = Arrays.stream(tagsList.split(","))
                .filter(tag -> (tag.length() >= 1)).collect(Collectors.toList());
        List<Tag> tags = tagService.saveTags(tagTokens, savedPost.getId());
        postTagService.savePostTags(tags, savedPost);
        return ResponseEntity.ok("Post save Successfully");
    }

    @PostMapping(path = "updatepost/update")
    public ResponseEntity<String> updatePost(@ModelAttribute Post post, @RequestParam("tagsData") String tags,
            Model model) {
        List<String> tagTokens = Arrays.stream(tags.split(","))
                .filter(tag -> (tag.length() >= 1)).collect(Collectors.toList());
        List<Tag> tagsList = tagService.saveTags(tagTokens, post.getId());
        postTagService.deletePostTags(post);
        postService.updatePost(post);
        postTagService.savePostTags(tagsList, post);
        return ResponseEntity.ok("Post update Successfully!");
    }

}
