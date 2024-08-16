package com.kacper.travelApp.service;

import com.kacper.travelApp.model.Plan;
import com.kacper.travelApp.repository.PlanRepository;
import org.springframework.stereotype.Service;

@Service
public class PlanServiceImpl implements PlanService {

    private PlanRepository planRepository;

    public PlanServiceImpl(PlanRepository planRepository) {
        this.planRepository = planRepository;
    }

    @Override
    public Plan savePlan(Plan plan) {
        return planRepository.save(plan);
    }
}
