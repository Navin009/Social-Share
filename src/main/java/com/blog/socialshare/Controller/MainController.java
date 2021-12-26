package com.blog.socialshare.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public String getPost(
            @RequestParam("start") Integer start,
            @RequestParam("limit") Integer limit,
            @RequestParam(value = "tagId", required = false, defaultValue = "") List<Integer> tagId,
            @RequestParam(value = "authorId", required = false, defaultValue = "") List<Integer> authorId,
            Model model) {
        List<Post> posts;

        HashMap<Post, List<String>> postWithTags = new HashMap<>();
        if (authorId.size() == 0) {
            if (tagId.size() == 0) {
                posts = postService.getPostsPage(start, limit);
            } else {
                posts = postService.getPostsPageByTagId(tagId, start, limit);
            }

        } else {

            if (tagId.size() == 0) {
                posts = postService.getPostsPageByAuthorId(authorId, start, limit);
            } else {
                posts = postService.getPostsPageByAuthorIdAndTagId(authorId, tagId, start, limit);
            }
        }

        for (Post post : posts) {
            postWithTags.put(post, postService.getTagNames(post.getId()));
        }

        model.addAttribute("posts", posts);
        model.addAttribute("postWithTags", postWithTags);
        int currentPage = start / limit + 1;
        model.addAttribute("page", currentPage);
        model.addAttribute("prevDisabled", currentPage <= 1);
        model.addAttribute("nextDisabled", currentPage >= 3);
        return "index";
    }

    @GetMapping(params = { "start", "limit", "search" })
    public String getPostBySearch(
            @RequestParam("start") Integer start, @RequestParam("limit") Integer limit,
            @RequestParam("search") String search,
            @RequestParam(value = "tagId", required = false, defaultValue = "") List<Integer> tagId,
            @RequestParam(value = "authorId", required = false, defaultValue = "") List<Integer> authorId,
            Model model) {
        List<Post> posts;
        Map<Post, List<String>> postWithTags = new HashMap<>();

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

        for (Post post : posts) {
            postWithTags.put(post, postService.getTagNames(post.getId()));
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
    public String getPostBysortField(
            @RequestParam("start") Integer start,
            @RequestParam("limit") Integer limit,
            @RequestParam("sortField") String sortField,
            @RequestParam(value = "order", required = false, defaultValue = "desc") String order,
            @RequestParam(value = "tagId", required = false, defaultValue = "") List<Integer> tagIds,
            @RequestParam(value = "authorId", required = false, defaultValue = "") List<Integer> authorIds,
            Model model) {

        List<Post> posts;
        Map<Post, List<String>> postWithTags = new HashMap<>();
        String sortFieldColumn = "author.name";

        if (sortField.equals("published_date"))
            sortFieldColumn = "publishedAt";

        if (authorIds.size() == 0) {
            if (tagIds.size() == 0) {
                posts = postService.getPostsAndSorted(start, limit, sortFieldColumn, order);
            } else {
                posts = postService.getPostsByTagIdAndSorted(start, limit, sortFieldColumn, tagIds, order);
            }

        } else {
            if (tagIds.size() == 0) {
                posts = postService.getPostsByAuthorIdAndSorted(start, limit, sortFieldColumn, authorIds, order);
            } else {
                posts = postService.getPostsByAuthorIdAndTagIdAndSorted(start, limit, sortFieldColumn, authorIds,
                        tagIds, order);
            }
        }

        for (Post post : posts) {
            postWithTags.put(post, postService.getTagNames(post.getId()));
        }
        model.addAttribute("postWithTags", postWithTags);
        model.addAttribute("posts", posts);
        return "index";
    }

    @GetMapping(params = { "sortField", "start", "limit", "search" })
    public String getPostBysortFieldBySearch(
            @RequestParam("start") Integer start,
            @RequestParam("limit") Integer limit,
            @RequestParam("sortField") String sortField,
            @RequestParam(value = "search") String searchQuery,
            @RequestParam(value = "order", required = false, defaultValue = "desc") String order,
            @RequestParam(value = "tagId", required = false, defaultValue = "") List<Integer> tagIds,
            @RequestParam(value = "authorId", required = false, defaultValue = "") List<Integer> authorIds,
            Model model) {

        List<Post> posts;
        HashMap<Post, List<String>> postWithTags = new HashMap<>();
        String sortFieldColumn = "author.name";

        if (sortField.equals("published_date"))
            sortFieldColumn = "publishedAt";

        if (authorIds.size() == 0) {
            if (tagIds.size() == 0) {
                posts = postService.getPostsBySearchAndSorted(searchQuery, start, limit, sortFieldColumn, order);
            } else {
                posts = postService.getPostsBySearchAndTagIdAndSorted(searchQuery, start, limit, sortFieldColumn,
                        tagIds, order);
            }

        } else {
            if (tagIds.size() == 0) {
                posts = postService.getPostsBySearchAndAuthorIdAndSorted(searchQuery, start, limit,
                        sortFieldColumn, authorIds, order);
            } else {
                posts = postService.getPostsBySearchAndAuthorIdAndTagIdAndSorted(searchQuery, start, limit,
                        sortFieldColumn, authorIds, tagIds, order);
            }
        }

        for (Post post : posts) {
            postWithTags.put(post, postService.getTagNames(post.getId()));
        }
        model.addAttribute("postWithTags", postWithTags);
        model.addAttribute("posts", posts);
        return "index";
    }

}