package com.system.fsharksocialmedia.repositories;

import com.system.fsharksocialmedia.entities.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like, Integer> {
}