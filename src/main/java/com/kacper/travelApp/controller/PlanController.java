package com.kacper.travelApp.controller;

import ch.qos.logback.core.status.ErrorStatus;
import com.kacper.travelApp.model.Plan;
import com.kacper.travelApp.model.Session;
import com.kacper.travelApp.model.dto.OverwritePlanDto;
import com.kacper.travelApp.model.dto.PlanDto;
import com.kacper.travelApp.repository.PlanRepository;
import com.kacper.travelApp.repository.SessionRepository;
import com.kacper.travelApp.service.PlanService;
import com.kacper.travelApp.service.SessionService;
import jakarta.servlet.http.HttpSession;
import org.hibernate.type.descriptor.java.ObjectJavaType;
import org.springframework.http.HttpStatus;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/protected/plan")
public class PlanController {
    private final PlanService planService;
    private final PlanRepository planRepository;
    private final SessionRepository sessionRepository;

    public PlanController(PlanService planService, PlanRepository planRepository, SessionRepository sessionRepository) {
        this.planService = planService;
        this.planRepository = planRepository;
        this.sessionRepository = sessionRepository;
    }

    @PostMapping("/savePlan")
    public ResponseEntity<String> savePlan(HttpSession httpSession, @RequestBody PlanDto planDto) {
        Optional<Session> session = sessionRepository.findSessionByJSSESSIONID(httpSession.getId());
        if (!session.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        long userId = session.get().getUserId();
        if (planService.checkIfAmountOfPlansIsOverTreshold(userId, 10)) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Plan plan = new Plan(userId, planDto.getHotel(), planDto.getAirportArrival(), planDto.getAirportDeparture());
        planService.savePlan(plan);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/overwritePlan")
    public ResponseEntity<String> savePlan(HttpSession httpSession, @RequestBody OverwritePlanDto overwritePlanDto) {
        Optional<Session> session = sessionRepository.findSessionByJSSESSIONID(httpSession.getId());
        if (!session.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Optional<Plan> planOptional = planRepository.findPlansById(overwritePlanDto.getId());
        if (planOptional.isPresent() == false) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        Plan plan = planOptional.get();
        plan.setHotel(overwritePlanDto.getHotel());
        plan.setAirportArrival(overwritePlanDto.getAirportArrival());
        plan.setAirportDeparture(overwritePlanDto.getAirportDeparture());
        planService.savePlan(plan);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/getPlans")
    public ResponseEntity<?> getPlans(HttpSession httpSession) {
        Optional<Session> session = sessionRepository.findSessionByJSSESSIONID(httpSession.getId());
        if (!session.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        long userId = session.get().getUserId();
        List<Plan> plans = planRepository.findPlansByUserId(userId);
        if (plans.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(plans, HttpStatus.OK);
    }

    @PostMapping("/getPopularPlans")
    public ResponseEntity<?> getPopularPlans() {
        List<Plan> plans = planRepository.findAll();
        return new ResponseEntity<>(plans, HttpStatus.OK);
    }


}

