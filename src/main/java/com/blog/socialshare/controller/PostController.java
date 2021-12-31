package com.blog.socialshare.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.blog.socialshare.model.Comment;
import com.blog.socialshare.model.Post;
import com.blog.socialshare.model.Tag;
import com.blog.socialshare.model.User;
import com.blog.socialshare.service.CommentService;
import com.blog.socialshare.service.PostService;
import com.blog.socialshare.service.PostTagService;
import com.blog.socialshare.service.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
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
    public String blogPage(Model model) {
        return "redirect:/";
    }

    @GetMapping("/blog/{postid}")
    public String getPostById(@PathVariable("postid") Integer postId, Model model) {
        Post post = postService.getPostById(postId);
        List<Comment> comments = commentService.getCommentsByPostId(post);
        List<Tag> tags = postTagService.getTagsByPostId(post);
        model.addAttribute("postid", postId);
        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        model.addAttribute("tags", tags);
        return "blog";
    }

    @DeleteMapping("/blog/delete/{postid}")
    @ResponseBody
    public String deletePost(@PathVariable("postid") Integer postId) {
        postService.deletePost(postId);
        return "success";
    }

    @GetMapping(path = "newpost")
    public String newPost(@SessionAttribute("loggedUser") User user, Model model) {
        model.addAttribute("author", user);
        return "newpost";
    }

    @PostMapping(path = "/newpost/save")
    public String savePost(@ModelAttribute("post") Post post, @SessionAttribute("loggedUser") User user,
            @RequestParam("tagsData") String tagsList, Model model) {
        post.setAuthor(user);
        Post savedPost = postService.savePost(post);
        List<String> tagTokens = Arrays.stream(tagsList.split(","))
                .filter(tag -> (tag.length() >= 1)).collect(Collectors.toList());
        List<Tag> tags = tagService.saveTags(tagTokens, savedPost.getId());
        postTagService.savePostTags(tags, savedPost);
        model.addAttribute("postSaved", true);
        return "redirect:/";
    }

    @GetMapping(path = "updatepost/{postId}")
    public String updatePost(@PathVariable("postId") Integer postId, Model model) {
        Post post = postService.getPostById(postId);
        List<Tag> tags = postTagService.getTagsByPostId(post);
        model.addAttribute("tags", tags);
        model.addAttribute("post", post);
        return "updatepost";
    }

    @PostMapping(path = "updatepost/update")
    public String updatePost(@ModelAttribute Post post, @RequestParam("tagsData") String tags, Model model) {
        List<String> tagTokens = Arrays.stream(tags.split(","))
                .filter(tag -> (tag.length() >= 1)).collect(Collectors.toList());
        List<Tag> tagsList = tagService.saveTags(tagTokens, post.getId());
        postTagService.deletePostTags(post);
        postService.updatePost(post);
        postTagService.savePostTags(tagsList, post);
        return "redirect:/blog/" + post.getId();
    }   

}
