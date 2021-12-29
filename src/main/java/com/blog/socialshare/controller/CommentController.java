package com.blog.socialshare.controller;

import javax.servlet.http.HttpServletRequest;

import com.blog.socialshare.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class CommentController {

    @Autowired
    private CommentService commentService;

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

    @PutMapping(path = "updatecomment/{commentId}")
    @ResponseBody
    public String updateComment(@PathVariable("commentId") Integer commentId, @RequestBody String comment) {
        commentService.updateComment(commentId, comment);
        return "success";
    }

    @DeleteMapping(path = "deletecomment/{commentId}")
    @ResponseBody
    public String deleteComment(@PathVariable("commentId") Integer commentId) {
        commentService.deleteComment(commentId);
        return "success";
    }

}
