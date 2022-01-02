package com.blog.socialshare.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.blog.socialshare.dto.TagDTO;
import com.blog.socialshare.model.Tag;
import com.blog.socialshare.repository.TagRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public List<Tag> saveTags(List<String> tagNames, Integer PostId) {
        List<Tag> tags = new ArrayList<>();
        for (String tagName : tagNames) {
            Tag searchedTag = tagRepository.findOneByName(tagName);
            if (searchedTag == null) {
                Tag tag = new Tag();
                tag.setName(tagName);
                tag.setCreatedAt(new Date());
                tag.setUpdatedAt(new Date());
                searchedTag = tagRepository.save(tag);
            }
            tags.add(searchedTag);
        }
        return tags;
    }

    public List<Tag> getTagsByName(String tagName) {
        return tagRepository.findTagByName(tagName);
    }

    public List<Tag> getTags(List<Integer> tagIds) {
        return tagRepository.findAllById(tagIds);
    }

    public List<TagDTO> getTagsByPostId(Integer postId) {
        return tagRepository.getTagsByPostId(postId);
    }

}
