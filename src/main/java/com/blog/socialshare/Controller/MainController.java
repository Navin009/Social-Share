package com.blog.socialshare.Controller;

import java.util.List;

import com.blog.socialshare.Model.Post;
import com.blog.socialshare.Service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    PostService postService;

    @GetMapping(path = "")
    public String indexPage(Model model) {
        return "redirect:/?start=0&limit=10";
    }

    @GetMapping(params = "search")
    public String searchPost(@RequestParam(value = "search") String search,
            Model model) {
        List<Post> posts = postService.searchPost(search);
        model.addAttribute("posts", posts);
        return "index";
    }

    @GetMapping(params = { "start", "limit" })
    public String paginateIndexPage(@RequestParam("start") int start,
            @RequestParam("limit") int limit, Model model) {
        List<Post> posts = postService.getPosts(start, limit);
        model.addAttribute("posts", posts);
        int currentPage = start / limit + 1;

        model.addAttribute("page", currentPage);
        model.addAttribute("prevDisabled", currentPage <= 1);
        model.addAttribute("nextDisabled", currentPage >= 3);
        return "index";
    }

    @GetMapping(params = { "author" })
    public String authorPage(@RequestParam("author") String author, Model model) {
        // List<Post> posts = postService.getPostsByAuthor(author);
        // model.addAttribute("posts", posts);
        System.out.println("author");
        return "index";
    }

}