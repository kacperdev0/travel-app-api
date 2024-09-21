package com.kacper.travelApp.controller;

import com.kacper.travelApp.model.Plan;
import com.kacper.travelApp.model.Post;
import com.kacper.travelApp.model.Session;
import com.kacper.travelApp.model.dto.OverwritePlanDto;
import com.kacper.travelApp.model.dto.PlanDto;
import com.kacper.travelApp.repository.PlanRepository;
import com.kacper.travelApp.repository.PostRepository;
import com.kacper.travelApp.repository.SessionRepository;
import com.kacper.travelApp.service.Service.PlanService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("api/protected/plan")
public class PlanController {
    private final PlanService planService;
    private final PlanRepository planRepository;
    private final SessionRepository sessionRepository;

    private final PostRepository postRepository;

    public PlanController(PlanService planService, PlanRepository planRepository, SessionRepository sessionRepository, PostRepository postRepository) {
        this.planService = planService;
        this.planRepository = planRepository;
        this.sessionRepository = sessionRepository;
        this.postRepository = postRepository;
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
        plan.setPublic(false);
        planService.savePlan(plan);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/getPlans")
    public ResponseEntity<?> getPlans(HttpSession httpSession) {
        System.out.println("GetPlans");
        Optional<Session> session = sessionRepository.findSessionByJSSESSIONID(httpSession.getId());
        if (!session.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        long userId = session.get().getUserId();
        List<Plan> plans = planRepository.findPlansByUserId(userId);
        return new ResponseEntity<>(plans, HttpStatus.OK);
    }

    @PostMapping("/togglePublicity/{id}")
    public ResponseEntity<?> togglePublicity(@PathVariable long id) {
        Optional<Plan> plan = planRepository.findPlansById(id);

        if (!plan.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Plan currentPlan = plan.get();

        if (!currentPlan.isPublic() && !postRepository.existsByPlanId(currentPlan.getId())) {
            postRepository.save(new Post(currentPlan));
        }

        currentPlan.setPublic(!currentPlan.isPublic());
        planRepository.save(currentPlan);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}

