package com.kacper.travelApp.service;

import com.kacper.travelApp.model.Plan;
import jakarta.servlet.http.HttpSession;

public interface PlanService {
    public Plan savePlan(Plan plan);
    public boolean checkIfAmountOfPlansIsOverTreshold(long userId, int treshold);
}
