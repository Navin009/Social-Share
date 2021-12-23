package com.blog.socialshare.Repository;

import com.blog.socialshare.Model.Tag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

    @Query("Select t from Tag t where upper(t.name) like :name")
    Tag findOneByName(@Param("name") String tagName);

}
