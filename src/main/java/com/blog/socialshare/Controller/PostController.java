package com.blog.socialshare.Controller;

import java.util.List;

import com.blog.socialshare.model.Comment;
import com.blog.socialshare.model.Post;
import com.blog.socialshare.model.Tag;
import com.blog.socialshare.model.User;
import com.blog.socialshare.repository.UserRepository;
import com.blog.socialshare.service.CommentService;
import com.blog.socialshare.service.PostService;
import com.blog.socialshare.service.PostTagService;
import com.blog.socialshare.service.TagService;
import com.blog.socialshare.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TagService tagService;

    @Autowired
    PostTagService postTagService;

    @Autowired
    CommentService commentService;

    @Autowired
    private UserService userService;

    @RequestMapping(path = "/blog")
    public String pageblog(Model model) {
        return "redirect:/";
    }

    @GetMapping("/blog/{postid}")
    public String getPostById(@PathVariable("postid") Integer postId, Model model) {
        Post post = postService.getPostById(postId);
        List<Comment> comments = commentService.getCommentsByPostId(post);
        List<Tag> tags = postTagService.getTagsByPostId(post);
        model.addAttribute("postid", postId);
        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        model.addAttribute("tags", tags);
        return "blog";
    }

    @DeleteMapping("/blog/delete/{postid}")
    @ResponseBody
    public String deletePost(@PathVariable("postid") Integer postId) {
        postService.deletePost(postId);
        return "success";
    }

    @GetMapping(path = "newpost")
    public String newPost(Model model) {
        User user = new User();
        user.setName("Navin Kumar");
        model.addAttribute("author", user);
        return "newpost";
    }

    @PostMapping(path = "newpost/save")
    public String savePost(@ModelAttribute("post") Post post, @RequestParam("tags") String tagsList, Model model) {
        User user = userService.getUserById(3);
        post.setAuthor(user);
        Post savedPost = postService.savePost(post);
        String[] tagtokens = tagsList.split("\r\n");
        List<Tag> tags = tagService.saveTag(tagtokens, savedPost.getId());
        postTagService.savePostTag(tags, savedPost);
        model.addAttribute("postSaved", true);
        return "redirect:/";
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
