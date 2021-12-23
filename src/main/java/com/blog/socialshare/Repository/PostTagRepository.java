package com.blog.socialshare.Repository;

import com.blog.socialshare.Model.Post;
import com.blog.socialshare.Model.PostTag;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostTagRepository extends JpaRepository<PostTag, Integer> {

}
