package com.a1assignment.repository;

import com.a1assignment.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT p FROM Post p WHERE p.nickname LIKE %:nickname%")
    List<Post> findByNickname(@Param("nickname") String nickname);

    @Query("SELECT p FROM Post p WHERE p.title LIKE %:title%")
    List<Post> findByTitle(@Param("title") String title);
}
