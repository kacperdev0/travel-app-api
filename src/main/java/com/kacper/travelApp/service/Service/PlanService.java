package com.kacper.travelApp.service.Service;

import com.kacper.travelApp.model.Plan;
import jakarta.servlet.http.HttpSession;

public interface PlanService {
    public Plan savePlan(Plan plan);
    public boolean checkIfAmountOfPlansIsOverTreshold(long userId, int treshold);
}
