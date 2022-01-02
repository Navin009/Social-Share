package com.blog.socialshare.repository;

import java.util.List;

import com.blog.socialshare.dto.TagDTO;
import com.blog.socialshare.model.Tag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

    @Query("Select t from Tag t where name like :name")
    Tag findOneByName(@Param("name") String tagName);

    @Query("select t from Tag t where upper(name) like upper(concat('%',:name,'%'))")
    List<Tag> findTagByName(@Param("name") String tagName);

    @Query("select new com.blog.socialshare.dto.TagDTO(t.id, t.name) from Tag t, PostTag pt where pt.tagId = t.id and pt.postId.id = :postId ")
    List<TagDTO> getTagsByPostId(@Param("postId") Integer postId);

}
