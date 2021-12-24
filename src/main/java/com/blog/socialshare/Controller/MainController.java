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

    @GetMapping(params = { "start", "limit" })
    public String searchPost(
            @RequestParam(value = "search", required = false, defaultValue = "") String search,
            @RequestParam("start") Integer start,
            @RequestParam("limit") Integer limit,
            Model model) {

        List<Post> posts;
        if (search == "")
            posts = postService.getPosts(start, limit);
        else
            posts = postService.getPostsBySearch(search, start, limit);

        model.addAttribute("posts", posts);
        int currentPage = start / limit + 1;
        model.addAttribute("page", currentPage);
        model.addAttribute("prevDisabled", currentPage <= 1);
        model.addAttribute("nextDisabled", currentPage >= 3);
        return "index";
    }

    @GetMapping(params = { "sortField", "start", "limit" })
    public String authorPage(
            @RequestParam("start") Integer start,
            @RequestParam("limit") Integer limit,
            @RequestParam("sortField") String sortField,
            @RequestParam(value = "search", required = false, defaultValue = "") String searchQuery,
            @RequestParam(value = "order", required = false, defaultValue = "desc") String order,
            Model model) {

        List<Post> posts;
        if (searchQuery != "") {
            if (sortField.equals("author_name"))
                posts = postService.getPostsAndSorted(start, limit, "author.name", order);
            else
                posts = postService.getPostsAndSorted(start, limit, "publishedAt", order);

        } else {
            if (sortField.equals("author_name"))
                posts = postService.getPostsBySearchAndSorted(searchQuery, start, limit, "author.name", order);
            else
                posts = postService.getPostsBySearchAndSorted(searchQuery, start, limit, "publishedAt", order);
        }
        model.addAttribute("posts", posts);
        return "index";
    }

}