package com.blog.socialshare.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.blog.socialshare.model.Post;
import com.blog.socialshare.model.Tag;
import com.blog.socialshare.model.User;
import com.blog.socialshare.service.PostService;
import com.blog.socialshare.service.TagService;
import com.blog.socialshare.service.UserService;

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

    @Autowired
    private TagService tagService;

    @Autowired
    private UserService userService;

    @GetMapping("")
    public String indexPage(Model model) {
        return "redirect:/?start=0&limit=10";
    }

    @GetMapping(params = { "start", "limit" })
    public String getPost(
            @RequestParam("start") Integer start,
            @RequestParam("limit") Integer limit,
            @RequestParam(value = "tagId", required = false, defaultValue = "") List<Integer> tagIds,
            @RequestParam(value = "authorId", required = false, defaultValue = "") List<Integer> authorIds,
            @RequestParam(value = "Date", required = false, defaultValue = "0001-01-01,9999-12-30") String dateFilter,
            Model model) {
        List<Post> posts;
        HashMap<Post, List<String>> postWithTags = new HashMap<>();
        String[] dates = dateFilter.split(",");
        try {
            Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(dates[0]);
            Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(dates[1]);
            if (authorIds.size() == 0) {
                if (tagIds.size() == 0) {
                    posts = postService.getPostsPage(start, limit, startDate, endDate);
                } else {
                    posts = postService.getPostsPageByTagId(tagIds, start, limit, startDate, endDate);
                }
            } else {
                if (tagIds.size() == 0) {
                    posts = postService.getPostsPageByAuthorId(authorIds, start, limit, startDate, endDate);
                } else {
                    posts = postService.getPostsPageByAuthorIdAndTagId(authorIds, tagIds, start, limit, startDate,
                            endDate);
                }
            }

            for (Post post : posts) {
                postWithTags.put(post, postService.getTagNames(post.getId()));
            }

            List<Tag> tags = tagService.getTags(tagIds);
            Iterable<User> authors = userService.getUsers(authorIds);

            model.addAttribute("tags", tags);
            model.addAttribute("authors", authors);
            model.addAttribute("posts", posts);
            model.addAttribute("postWithTags", postWithTags);
            int currentPage = start / limit + 1;
            model.addAttribute("page", currentPage);
            model.addAttribute("prevDisabled", currentPage <= 1);
            model.addAttribute("nextDisabled", currentPage >= 3);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "index";
    }

    @GetMapping(params = { "start", "limit", "search" })
    public String getPostBySearch(
            @RequestParam("start") Integer start, @RequestParam("limit") Integer limit,
            @RequestParam("search") String search,
            @RequestParam(value = "tagId", required = false, defaultValue = "") List<Integer> tagIds,
            @RequestParam(value = "authorId", required = false, defaultValue = "") List<Integer> authorIds,
            @RequestParam(value = "Date", required = false, defaultValue = "0001-12-01,9999-12-30") String dateFilter,
            Model model) {
        List<Post> posts;
        Map<Post, List<String>> postWithTags = new HashMap<>();
        String[] dates = dateFilter.split(",");
        try {
            Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(dates[0]);
            Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(dates[1]);
            if (authorIds.size() == 0) {
                if (tagIds.size() == 0) {
                    posts = postService.getPostsBySearch(search, start, limit, startDate, endDate);
                } else {
                    posts = postService.getPostsBySearchAndTagId(search, tagIds, start, limit, startDate, endDate);
                }
            } else {
                if (tagIds.size() == 0) {
                    posts = postService.getPostsBySearchAndAuthorId(search, authorIds, start, limit, startDate,
                            endDate);
                } else {
                    posts = postService.getPostsBySearchAndAuthorIdAndTagId(search, authorIds, tagIds, start, limit,
                            startDate, endDate);
                }
            }

            for (Post post : posts) {
                postWithTags.put(post, postService.getTagNames(post.getId()));
            }

            List<Tag> tags = tagService.getTags(tagIds);
            Iterable<User> authors = userService.getUsers(authorIds);

            model.addAttribute("tags", tags);
            model.addAttribute("authors", authors);
            model.addAttribute("posts", posts);
            model.addAttribute("postWithTags", postWithTags);

            int currentPage = start / limit + 1;
            model.addAttribute("page", currentPage);
            model.addAttribute("prevDisabled", currentPage <= 1);
            model.addAttribute("nextDisabled", currentPage >= 3);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
            @RequestParam(value = "Date", required = false, defaultValue = "0001-12-01,9999-12-30") String dateFilter,
            Model model) {

        List<Post> posts;
        Map<Post, List<String>> postWithTags = new HashMap<>();
        String sortFieldColumn = "author.name";
        String[] dates = dateFilter.split(",");
        try {
            Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(dates[0]);
            Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(dates[1]);
            if (sortField.equals("published_date"))
                sortFieldColumn = "publishedAt";

            if (authorIds.size() == 0) {
                if (tagIds.size() == 0) {
                    posts = postService.getPostsAndSorted(start, limit, sortFieldColumn, order, startDate, endDate);
                } else {
                    posts = postService.getPostsByTagIdAndSorted(start, limit, sortFieldColumn, tagIds, order,
                            startDate, endDate);
                }

            } else {
                if (tagIds.size() == 0) {
                    posts = postService.getPostsByAuthorIdAndSorted(start, limit, sortFieldColumn, authorIds, order,
                            startDate, endDate);
                } else {
                    posts = postService.getPostsByAuthorIdAndTagIdAndSorted(start, limit, sortFieldColumn, authorIds,
                            tagIds, order, startDate, endDate);
                }
            }

            for (Post post : posts) {
                postWithTags.put(post, postService.getTagNames(post.getId()));
            }

            List<Tag> tags = tagService.getTags(tagIds);
            Iterable<User> authors = userService.getUsers(authorIds);

            model.addAttribute("tags", tags);
            model.addAttribute("authors", authors);
            model.addAttribute("postWithTags", postWithTags);
            model.addAttribute("posts", posts);
        } catch (ParseException e) {
            e.printStackTrace();
        }
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
            @RequestParam(value = "Date", required = false, defaultValue = "0001-12-01,9999-12-30") String dateFilter,
            Model model) {

        List<Post> posts;
        Map<Post, List<String>> postWithTags = new HashMap<>();
        String sortFieldColumn = "author.name";

        if (sortField.equals("published_date"))
            sortFieldColumn = "publishedAt";

        String[] dates = dateFilter.split(",");
        try {
            Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(dates[0]);
            Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(dates[1]);

            if (authorIds.size() == 0) {
                if (tagIds.size() == 0) {
                    posts = postService.getPostsBySearchAndSorted(searchQuery, start, limit, sortFieldColumn, order,
                            startDate, endDate);
                } else {
                    posts = postService.getPostsBySearchAndTagIdAndSorted(searchQuery, start, limit, sortFieldColumn,
                            tagIds, order, startDate, endDate);
                }

            } else {
                if (tagIds.size() == 0) {
                    posts = postService.getPostsBySearchAndAuthorIdAndSorted(searchQuery, start, limit,
                            sortFieldColumn, authorIds, order, startDate, endDate);
                } else {
                    posts = postService.getPostsBySearchAndAuthorIdAndTagIdAndSorted(searchQuery, start, limit,
                            sortFieldColumn, authorIds, tagIds, order, startDate, endDate);
                }
            }

            for (Post post : posts) {
                postWithTags.put(post, postService.getTagNames(post.getId()));
            }

            List<Tag> tags = tagService.getTags(tagIds);
            Iterable<User> authors = userService.getUsers(authorIds);

            model.addAttribute("tags", tags);
            model.addAttribute("authors", authors);
            model.addAttribute("postWithTags", postWithTags);
            model.addAttribute("posts", posts);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "index";
    }

}