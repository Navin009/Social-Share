package com.blog.socialshare.Service;

import java.util.Date;
import java.util.List;

import com.blog.socialshare.Model.Post;
import com.blog.socialshare.Model.PostTag;
import com.blog.socialshare.Model.Tag;
import com.blog.socialshare.Repository.PostTagRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostTagService {

    @Autowired
    private PostTagRepository postTagRepository;

    public void savePostTag(List<Tag> tags, Post post) {
        for (Tag tag : tags) {
            PostTag postTag = new PostTag();
            postTag.setTagId(tag);
            postTag.setPostId(post);
            postTag.setCreatedAt(new Date());
            postTag.setUpdatedAt(new Date());
            postTagRepository.save(postTag);
        }
    }

    public List<Tag> getTagsByPostId(Post post) {
        List<Tag> tags = post.getPostTags()
                .stream().map(postTag -> postTag.getTagId())
                .collect(java.util.stream.Collectors.toList());

        return tags;
    }
}
