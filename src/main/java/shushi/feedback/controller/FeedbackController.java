package shushi.feedback.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shushi.feedback.dto.FeedbackDto;
import shushi.feedback.entity.FeedbackEntity;
import shushi.feedback.servcie.FeedbackService;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;
    // Get all feedback
    @GetMapping("all/{productId}")
    public ResponseEntity<List<FeedbackEntity>> getAllFeedback(@PathVariable String productId) {
        List<FeedbackEntity> feedbackList = feedbackService.getAllFeedback(productId);
        return new ResponseEntity<>(feedbackList, HttpStatus.OK);
    }
    // Get feedback by ID
    @GetMapping("/one/{id}")
    public ResponseEntity<FeedbackEntity> getFeedbackById(@PathVariable String id) {
        FeedbackEntity feedback = feedbackService.getFeedbackById(id);
        return new ResponseEntity<>(feedback, HttpStatus.OK);
    }

    // Create feedback
    @PostMapping("/private/new")
    public ResponseEntity<FeedbackEntity> createFeedback(@RequestBody FeedbackDto feedback) {
        FeedbackEntity createdFeedback = feedbackService.createFeedback(feedback);
        return new ResponseEntity<>(createdFeedback, HttpStatus.CREATED);
    }
}

