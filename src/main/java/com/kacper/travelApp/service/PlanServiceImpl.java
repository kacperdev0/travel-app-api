package com.kacper.travelApp.service;

import com.kacper.travelApp.model.Plan;
import com.kacper.travelApp.repository.PlanRepository;
import com.kacper.travelApp.service.Service.PlanService;
import org.springframework.stereotype.Service;
import java.util.List;

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

    @Override
    public boolean checkIfAmountOfPlansIsOverTreshold(long userId, int treshold) {
        List<Plan> plans = planRepository.findPlansByUserId(userId);
        if (plans.size() > treshold) {
            return true;
        }
        return false;
    }
}
