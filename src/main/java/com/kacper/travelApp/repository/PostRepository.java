package com.kacper.travelApp.repository;

import com.kacper.travelApp.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {
    public boolean existsByPlanId(long id);
}
