package com.blog.socialshare.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.blog.socialshare.Model.Comment;
import com.blog.socialshare.Model.Post;
import com.blog.socialshare.Service.CommentService;
import com.blog.socialshare.Service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    PostService postService;

    @Autowired
    CommentService commentService;

    @RequestMapping(path = "")
    public String pageblog(Model model) {
        return "redirect:/";
    }

    @GetMapping("/{postid}")
    public String getPostById(@PathVariable("postid") Integer postId, Model model) {
        Post post = postService.getPostById(postId);
        List<Comment[]> comments = commentService.getCommentsByPostId(post);
        model.addAttribute("postid", postId);
        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        return "blog";
    }

    @PostMapping("/comment/save")
    public String saveComment(HttpServletRequest request) {
        System.out.println(request.getParameter("postid"));
        Integer postId = Integer.parseInt(request.getParameter("postid"));
        String comment = request.getParameter("comment");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        commentService.saveComment(postId, name, email, comment);
        return "redirect:/blog/" + postId;
    }

    @DeleteMapping("/delete/{postid}")
    @ResponseBody
    public String deletePost(@PathVariable("postid") Integer postId) {
        postService.deletePost(postId);
        return "success";
    }

}
