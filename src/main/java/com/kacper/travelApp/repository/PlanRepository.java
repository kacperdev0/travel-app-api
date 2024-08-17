package com.kacper.travelApp.repository;

import com.kacper.travelApp.model.Plan;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer> {
    List<Plan> findPlansByUserId(long userId);
}
