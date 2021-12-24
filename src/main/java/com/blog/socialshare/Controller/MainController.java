package com.blog.socialshare.Controller;

import java.util.HashMap;
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
            @RequestParam("start") Integer start,
            @RequestParam("limit") Integer limit,
            @RequestParam(value = "search", required = false, defaultValue = "") String search,
            @RequestParam(value = "tagId", required = false, defaultValue = "") List<Integer> tagId,
            @RequestParam(value = "authorId", required = false, defaultValue = "") List<Integer> authorId,
            Model model) {
        List<Post> posts;

        HashMap<Post, List<String>> postWithTags = new HashMap<>();
        if (search.equals("")) {

            if (authorId.size() == 0) {

                if (tagId.size() == 0) {
                    posts = postService.getPosts(start, limit);
                } else {
                    posts = postService.getPostsByTagId(tagId, start, limit);
                }

            } else {

                if (tagId.size() == 0) {
                    posts = postService.getPostsByAuthorId(authorId, start, limit);
                } else {
                    posts = postService.getPostsByAuthorIdAndTagId(authorId, tagId, start, limit);
                }
            }

        } else {

            if (authorId.size() == 0) {

                if (tagId.size() == 0) {
                    posts = postService.getPostsBySearch(search, start, limit);
                } else {
                    posts = postService.getPostsBySearchAndTagId(search, tagId, start, limit);
                }
            } else {
                if (tagId.size() == 0) {
                    posts = postService.getPostsBySearchAndAuthorId(search, authorId, start, limit);
                } else {
                    posts = postService.getPostsBySearchAndAuthorIdAndTagId(search, authorId, tagId, start, limit);
                }
            }
        }

        for (Post post : posts) {
            postWithTags.put(post, postService.getTags(post.getId()));
        }

        model.addAttribute("posts", posts);
        model.addAttribute("postWithTags", postWithTags);
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
            @RequestParam(value = "tagId", required = false, defaultValue = "") Integer[] tagId,
            @RequestParam(value = "authorId", required = false, defaultValue = "") Integer authorId,
            Model model) {

        List<Post> posts;
        HashMap<Post, List<String>> postWithTags = new HashMap<>();
        if (searchQuery.equals("")) {
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
        for (Post post : posts) {
            postWithTags.put(post, postService.getTags(post.getId()));
        }
        model.addAttribute("postWithTags", postWithTags);
        model.addAttribute("posts", posts);
        return "index";
    }

}