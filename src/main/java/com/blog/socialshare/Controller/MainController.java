package com.blog.socialshare.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import com.blog.socialshare.Dao.PostDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping(path = "")
    public String indexPage() {
        return "index";
    }

    @GetMapping(params = "search")
    public String searchPost(@RequestParam(value = "search") String search, Model model) {
        // model.addAttribute( "posts", posts);
        return "index";
    }

    @GetMapping(path = "newpost")
    public String newPost() {
        return "newpost";
    }

    @Autowired
    PostDao postDao;

    @PostMapping(path = "newpost/save")
    public String savePost(HttpServletRequest request) {
        String title = request.getParameter("title");
        String excerpt = request.getParameter("excerpt");
        String content = request.getParameter("content");
        postDao.savePost(title, excerpt, content);
        return "redirect:/newpost";
    }

    @GetMapping(path = "{postid}")
    public String getPost(@PathParam("postid") String postId) {
        return "index";
    }

}