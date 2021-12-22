package com.blog.socialshare.Controller;

import java.util.List;

import com.blog.socialshare.Model.Post;
import com.blog.socialshare.Service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    PostService postService;

    @GetMapping(path = "")
    public String indexPage(Model model) {
        List<Post> posts = postService.getPosts();
        model.addAttribute("posts", posts);
        return "redirect:/?start=0&limit=10";
    }

    @GetMapping(params = { "start", "limit" })
    public String paginateIndexPage(@RequestParam("start") int start, @RequestParam("limit") int limit, Model model) {
        List<Post> posts = postService.getPosts(start, limit);
        model.addAttribute("posts", posts);
        int currentPage = start / limit + 1;
        model.addAttribute("page", currentPage);
        model.addAttribute("prevDisabled", currentPage <= 1);
        model.addAttribute("nextDisabled", currentPage >= 3);
        return "index";
    }

    @GetMapping(params = "search")
    public String searchPost(@RequestParam(value = "search") String search, Model model) {
        model.addAttribute("posts", postService.searchPost(search));
        return "index";
    }

    @GetMapping(path = "newpost")
    public String newPost() {
        return "newpost";
    }

    @PostMapping(path = "newpost/save")
    public String savePost(@ModelAttribute("post") Post post, Model model) {
        model.addAttribute("postSaved", true);
        postService.savePost(post);
        return "redirect:/newpost";
    }

    @GetMapping(path = "updatepost/{postId}")
    public String updatePost(@PathVariable("postId") Integer postId, Model model) {
        Post post = postService.getPostById(postId);
        model.addAttribute("post", post);
        return "updatepost";
    }

    @PostMapping(path = "updatepost/update")
    public String updatePost(@ModelAttribute("post") Post post, Model model) {
        postService.updatePost(post);
        return "redirect:/blog/" + post.getId();
    }

}