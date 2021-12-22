package com.blog.socialshare.Controller;

import com.blog.socialshare.Service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PutMapping(path = "updatecomment/{commentId}")
    @ResponseBody
    public String updateComment(@PathVariable("commentId") Integer commentId, @RequestBody String comment) {
        System.out.println(comment);
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
