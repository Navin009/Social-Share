package com.blog.socialshare.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.blog.socialshare.Model.Tag;
import com.blog.socialshare.Repository.TagRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;

    public List<Tag> saveTag(String[] tagNames, Integer PostId) {
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

}
