package com.blog.socialshare.Controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.blog.socialshare.Dao.CommentDao;
import com.blog.socialshare.Dao.PostDao;
import com.blog.socialshare.Model.Comment;
import com.blog.socialshare.Model.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/blog")
public class BlogController {

    @RequestMapping(path = "", params = "postid")
    public String pageblog(@RequestParam(value = "page") int val, Model model) {
        return "blog";
    }

    @Autowired
    PostDao postDao;

    @Autowired
    CommentDao commentDao;

    @GetMapping("/{postid}")
    public String getPostById(@PathVariable("postid") Integer postId, Model model) {
        Post post = postDao.getPostById(postId);
        List<Comment> comments = commentDao.getCommentsByPostId(postId);
        model.addAttribute("postid", postId);
        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        return "blog";
    }

    @PostMapping("/comment/save")
    public String saveComment(HttpServletRequest request) {
        Integer postId = Integer.parseInt(request.getParameter("postid"));
        String comment = request.getParameter("comment");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        commentDao.saveComment(postId, comment, name, email);
        return "redirect:/blog/" + postId;
    }

}
