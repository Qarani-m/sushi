package shushi.feedback.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shushi.feedback.dto.FeedbackDto;
import shushi.feedback.entity.FeedbackEntity;
import shushi.feedback.servcie.FeedbackService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;
    // Get all feedback
    @GetMapping("/public/all/{productId}")
    public ResponseEntity<Map<String, Object>> getAllFeedback(@PathVariable String productId) {
        Map<String, Object> feedbackResult = feedbackService.getAllFeedback(productId);

        if (feedbackResult.get("feedbackList") != null && !((List<?>) feedbackResult.get("feedbackList")).isEmpty()) {
            return ResponseEntity.ok(feedbackResult);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    // Get feedback by ID
    @GetMapping("/one/{id}")
    public ResponseEntity<Map<String, Object>> getFeedbackById(@PathVariable String id) {
        Map<String, Object> feedback = feedbackService.getFeedbackById(id);

        if (feedback != null) {
            return ResponseEntity.ok(feedback);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(feedback);
        }
    }


    // Create feedback
    @PostMapping("/private/new")
    public ResponseEntity<Map<String, Object>> createFeedback(@RequestBody FeedbackDto feedback) {
        Map<String, Object> creationResult = feedbackService.createFeedback(feedback);

        if (creationResult.get("createdFeedback") != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(creationResult);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(creationResult);
        }
    }

}

