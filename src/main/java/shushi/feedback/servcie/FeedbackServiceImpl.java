package shushi.feedback.servcie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shushi.feedback.dto.FeedbackDto;
import shushi.feedback.entity.FeedbackEntity;
import shushi.feedback.repository.FeedbackRepository;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class FeedbackServiceImpl implements FeedbackService{
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Override
    public Map<String, Object> getAllFeedback(String sushiId) {
        Map<String, Object> response = new HashMap<>();
        List<FeedbackEntity> feedbackList = feedbackRepository.getFeedbackBySushiId(sushiId);

        if (!feedbackList.isEmpty()) {
            response.put("feedbackList", feedbackList);
            response.put("message", "success");
        } else {
            response.put("feedbackList", null);
            response.put("message", "No feedback found for the given sushiId.");
        }

        return response;
    }


    @Override
    public Map<String, Object> getFeedbackById(String id) {
        Map<String, Object> response = new HashMap<>();
        Optional<FeedbackEntity> optionalFeedback = feedbackRepository.findById(id);

        if (optionalFeedback.isPresent()) {
            response.put("feedback", optionalFeedback.get());
            response.put("message", "success");
        } else {
            response.put("feedback", null);
            response.put("message", "Feedback not found.");
        }

        return response;
    }

    @Override
    public Map<String, Object> createFeedback(FeedbackDto feedbackDto) {
        Map<String, Object> response = new HashMap<>();

        if (feedbackDto != null) {
            FeedbackEntity feedbackEntity = FeedbackEntity.builder()
                    .userId(feedbackDto.getUserId())
                    .rating(feedbackDto.getRating())
                    .comment(feedbackDto.getComment())
                    .sushiId(feedbackDto.getSushiId())
                    .createdAt(new Date().toString())
                    .build();

            FeedbackEntity createdFeedback = feedbackRepository.save(feedbackEntity);
            response.put("createdFeedback", createdFeedback);
            response.put("message", "success");
        } else {
            response.put("createdFeedback", null);
            response.put("message", "Feedback cannot be null.");
        }

        return response;
    }


}
