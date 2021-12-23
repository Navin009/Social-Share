package com.blog.socialshare.Controller;

import java.util.List;

import com.blog.socialshare.Model.Comment;
import com.blog.socialshare.Model.Post;
import com.blog.socialshare.Model.Tag;
import com.blog.socialshare.Service.CommentService;
import com.blog.socialshare.Service.PostService;
import com.blog.socialshare.Service.PostTagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    PostService postService;

    @Autowired
    CommentService commentService;

    @Autowired
    PostTagService postTagService;

    @RequestMapping(path = "")
    public String pageblog(Model model) {
        return "redirect:/";
    }

    @GetMapping("/{postid}")
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

    @DeleteMapping("/delete/{postid}")
    @ResponseBody
    public String deletePost(@PathVariable("postid") Integer postId) {
        postService.deletePost(postId);
        return "success";
    }

}
