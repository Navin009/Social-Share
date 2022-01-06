package com.blog.socialshare.controller;

import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.blog.socialshare.dto.PostSummery;
import com.blog.socialshare.dto.TagDTO;
import com.blog.socialshare.service.PostService;
import com.blog.socialshare.service.TagService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class MainController {
    private static final int START_DATE = 0;
    private static final int END_DATE = 1;

    @Autowired
    PostService postService;

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "", method = { RequestMethod.GET, RequestMethod.POST })
    public ResponseEntity<String> indexPage(Model model) {
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create("?start=0&limit=10")).build();
    }

    @GetMapping(params = { "start", "limit" })
    public List<PostSummery> getPost(
            @RequestParam("start") Integer start,
            @RequestParam("limit") Integer limit,
            @RequestParam(value = "tagId", required = false, defaultValue = "") List<Integer> tagIds,
            @RequestParam(value = "authorId", required = false, defaultValue = "") List<Integer> authorIds,
            @RequestParam(value = "Date", required = false, defaultValue = "0001-01-01,9999-12-30") String dateFilter,
            Model model) throws ParseException {
        List<PostSummery> postSummeries;
        String[] dates = dateFilter.split(",");
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(dates[START_DATE]);
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(dates[END_DATE]);

        if (authorIds.size() == 0) {
            if (tagIds.size() == 0) {
                postSummeries = postService.getPostsPage(start, limit, startDate, endDate);
            } else {
                postSummeries = postService.getPostsPageByTagId(tagIds, start, limit, startDate, endDate);
            }
        } else {
            if (tagIds.size() == 0) {
                postSummeries = postService.getPostsPageByAuthorId(authorIds, start, limit, startDate, endDate);
            } else {
                postSummeries = postService.getPostsPageByAuthorIdAndTagId(authorIds, tagIds, start, limit,
                        startDate, endDate);
            }
        }
        for (PostSummery postSummery : postSummeries) {
            List<TagDTO> tags = tagService.getTagsByPostId(postSummery.getId());
            postSummery.setTags(tags);
        }
        return postSummeries;
    }

    @GetMapping(params = { "start", "limit", "search" })
    public List<PostSummery> getPostBySearch(
            @RequestParam("start") Integer start, @RequestParam("limit") Integer limit,
            @RequestParam("search") String search,
            @RequestParam(value = "tagId", required = false, defaultValue = "") List<Integer> tagIds,
            @RequestParam(value = "authorId", required = false, defaultValue = "") List<Integer> authorIds,
            @RequestParam(value = "Date", required = false, defaultValue = "0001-12-01,9999-12-30") String dateFilter,
            Model model) throws ParseException {
        List<PostSummery> postSummeries;
        String[] dates = dateFilter.split(",");

        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(dates[0]);
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(dates[1]);
        if (authorIds.size() == 0) {
            if (tagIds.size() == 0) {
                postSummeries = postService.getPostsBySearch(search, start, limit, startDate, endDate);
            } else {
                postSummeries = postService.getPostsBySearchAndTagId(search, tagIds, start, limit, startDate, endDate);
            }
        } else {
            if (tagIds.size() == 0) {
                postSummeries = postService.getPostsBySearchAndAuthorId(search, authorIds, start, limit, startDate,
                        endDate);
            } else {
                postSummeries = postService.getPostsBySearchAndAuthorIdAndTagId(search, authorIds, tagIds, start, limit,
                        startDate, endDate);
            }
        }
        for (PostSummery postSummery : postSummeries) {
            List<TagDTO> tags = tagService.getTagsByPostId(postSummery.getId());
            postSummery.setTags(tags);
        }
        return postSummeries;
    }

    @GetMapping(params = { "sortField", "start", "limit" })
    public List<PostSummery> getPostBysortField(
            @RequestParam("start") Integer start,
            @RequestParam("limit") Integer limit,
            @RequestParam("sortField") String sortField,
            @RequestParam(value = "order", required = false, defaultValue = "desc") String order,
            @RequestParam(value = "tagId", required = false, defaultValue = "") List<Integer> tagIds,
            @RequestParam(value = "authorId", required = false, defaultValue = "") List<Integer> authorIds,
            @RequestParam(value = "Date", required = false, defaultValue = "0001-12-01,9999-12-30") String dateFilter,
            Model model) throws ParseException {

        List<PostSummery> postSummeries;
        String sortFieldColumn = "author.name";
        String[] dates = dateFilter.split(",");
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(dates[0]);
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(dates[1]);
        if (sortField.equals("published_date"))
            sortFieldColumn = "publishedAt";

        if (authorIds.size() == 0) {
            if (tagIds.size() == 0) {
                postSummeries = postService.getPostsAndSorted(start, limit, sortFieldColumn, order, startDate, endDate);
            } else {
                postSummeries = postService.getPostsByTagIdAndSorted(start, limit, sortFieldColumn, tagIds, order,
                        startDate, endDate);
            }
        } else {
            if (tagIds.size() == 0) {
                postSummeries = postService.getPostsByAuthorIdAndSorted(start, limit, sortFieldColumn, authorIds, order,
                        startDate, endDate);
            } else {
                postSummeries = postService.getPostsByAuthorIdAndTagIdAndSorted(start, limit, sortFieldColumn,
                        authorIds,
                        tagIds, order, startDate, endDate);
            }
        }
        for (PostSummery postSummery : postSummeries) {
            List<TagDTO> tags = tagService.getTagsByPostId(postSummery.getId());
            postSummery.setTags(tags);
        }
        return postSummeries;
    }

    @GetMapping(params = { "sortField", "start", "limit", "search" })
    public List<PostSummery> getPostBysortFieldBySearch(
            @RequestParam("start") Integer start,
            @RequestParam("limit") Integer limit,
            @RequestParam("sortField") String sortField,
            @RequestParam(value = "search") String searchQuery,
            @RequestParam(value = "order", required = false, defaultValue = "desc") String order,
            @RequestParam(value = "tagId", required = false, defaultValue = "") List<Integer> tagIds,
            @RequestParam(value = "authorId", required = false, defaultValue = "") List<Integer> authorIds,
            @RequestParam(value = "Date", required = false, defaultValue = "0001-12-01,9999-12-30") String dateFilter,
            Model model) throws ParseException {

        List<PostSummery> postSummeries;
        String sortFieldColumn = "author.name";

        if (sortField.equals("published_date"))
            sortFieldColumn = "publishedAt";

        String[] dates = dateFilter.split(",");
        Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(dates[0]);
        Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(dates[1]);

        if (authorIds.size() == 0) {
            if (tagIds.size() == 0) {
                postSummeries = postService.getPostsBySearchAndSorted(searchQuery, start, limit, sortFieldColumn, order,
                        startDate, endDate);
            } else {
                postSummeries = postService.getPostsBySearchAndTagIdAndSorted(searchQuery, start, limit,
                        sortFieldColumn,
                        tagIds, order, startDate, endDate);
            }

        } else {
            if (tagIds.size() == 0) {
                postSummeries = postService.getPostsBySearchAndAuthorIdAndSorted(searchQuery, start, limit,
                        sortFieldColumn, authorIds, order, startDate, endDate);
            } else {
                postSummeries = postService.getPostsBySearchAndAuthorIdAndTagIdAndSorted(searchQuery, start, limit,
                        sortFieldColumn, authorIds, tagIds, order, startDate, endDate);
            }
        }
        for (PostSummery postSummery : postSummeries) {
            List<TagDTO> tags = tagService.getTagsByPostId(postSummery.getId());
            postSummery.setTags(tags);
        }
        return postSummeries;
    }

}