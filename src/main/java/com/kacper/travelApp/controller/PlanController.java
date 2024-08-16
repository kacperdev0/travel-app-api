package com.kacper.travelApp.controller;

import com.kacper.travelApp.model.Plan;
import com.kacper.travelApp.model.Session;
import com.kacper.travelApp.model.dto.PlanDto;
import com.kacper.travelApp.repository.PlanRepository;
import com.kacper.travelApp.repository.SessionRepository;
import com.kacper.travelApp.service.PlanService;
import com.kacper.travelApp.service.SessionService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
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
    private final SessionRepository sessionRepository;

    public PlanController(PlanService planService, SessionService sessionService, PlanRepository planRepository, SessionRepository sessionRepository) {
        this.planService = planService;
        this.sessionRepository = sessionRepository;
    }

    @PostMapping("/savePlan")
    public ResponseEntity<String> savePlan(HttpSession httpSession, @RequestBody PlanDto planDto) {
        Optional<Session> session = sessionRepository.findSessionByJSSESSIONID(httpSession.getId());
        if (!session.isPresent()) {
            return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
        }
        long userId = session.get().getUserId();
        Plan plan = new Plan(userId, planDto.getHotel(), planDto.getAirportArrival(), planDto.getAirportDeparture());
        planService.savePlan(plan);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
