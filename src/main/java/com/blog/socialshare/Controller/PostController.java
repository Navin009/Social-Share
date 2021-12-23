package com.blog.socialshare.Controller;

import java.util.List;

import com.blog.socialshare.Model.Post;
import com.blog.socialshare.Model.Tag;
import com.blog.socialshare.Model.User;
import com.blog.socialshare.Repository.UserRepository;
import com.blog.socialshare.Service.PostService;
import com.blog.socialshare.Service.PostTagService;
import com.blog.socialshare.Service.TagService;

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
public class PostController {

    @Autowired
    PostService postService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TagService tagService;

    @Autowired
    PostTagService postTagService;

    @GetMapping(path = "newpost")
    public String newPost() {
        return "newpost";
    }

    @PostMapping(path = "newpost/save")
    public String savePost(@ModelAttribute("post") Post post, @RequestParam("tags") String tagsList, Model model) {
        User user = userRepository.findById(3).get();
        post.setAuthor(user);
        Post savedPost = postService.savePost(post);
        String[] tagtokens = tagsList.split("\n");
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
