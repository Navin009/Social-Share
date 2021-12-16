package com.blog.socialshare.Repository;

import com.blog.socialshare.Model.PostTag;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostTagRepository extends CrudRepository<PostTag, Long> {

}
