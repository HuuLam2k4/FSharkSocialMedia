package com.system.fsharksocialmedia.repositories;

import com.system.fsharksocialmedia.entities.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PostRepository extends JpaRepository<Post, Integer> {
    Page<Post> findByContentContainingIgnoreCase(String title, Pageable pageable);

    long countByContentContainingIgnoreCase(String title);

    @Query(value = "EXEC GetPostsByYear :yearParam", nativeQuery = true)
    int getPostByYear(@Param("yearParam") int year);
}