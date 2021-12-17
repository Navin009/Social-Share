package com.blog.socialshare.Controller;

import java.util.Optional;

import com.blog.socialshare.Dao.PostDao;
import com.blog.socialshare.Model.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    PostDao postDao;

    @RequestMapping(path = "", params = "postid")
    public String pageblog(@RequestParam(value = "page") int val, Model model) {
        return "blog";
    }

    @GetMapping("/{postid}")
    public String getPostById(@PathVariable("postid") Integer postId, Model model) {
        Optional<Post> post = postDao.getPostById(postId);
        model.addAttribute("post", post.get());
        return "blog";
    }

}
