package com.a1assignment.repository;

import com.a1assignment.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.isDeleted = false AND (p.nickname LIKE %:keyword% OR p.title LIKE %:keyword%)")
    List<Post> findPostsByKeywords(@Param("keyword") String keyword);

    @Query("SELECT p FROM Post p WHERE p.isDeleted = false")
    List<Post> findAll();
}
