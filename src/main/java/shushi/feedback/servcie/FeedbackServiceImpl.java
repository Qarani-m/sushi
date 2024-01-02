package shushi.feedback.servcie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shushi.feedback.dto.FeedbackDto;
import shushi.feedback.entity.FeedbackEntity;
import shushi.feedback.repository.FeedbackRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FeedbackServiceImpl implements FeedbackService{
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Override
    public List<FeedbackEntity> getAllFeedback(String shushiId) {
        return feedbackRepository.getFeedbackByShushiId(shushiId);
    }

    @Override
    public FeedbackEntity getFeedbackById(String id) {
        Optional<FeedbackEntity> optionalFeedback = feedbackRepository.findById(id);
        return optionalFeedback.orElse(null);
    }
    @Override
    public FeedbackEntity createFeedback(FeedbackDto feedback) {
        if (feedback != null) {
            FeedbackEntity feedbackEntity = FeedbackEntity.builder()
                    .userId(feedback.getUserId())
                    .rating(feedback.getRating())
                    .comment(feedback.getComment())
                    .shushiId(feedback.getShushiId())
                    .createdAt(new Date().toString())
                    .build();
            return feedbackRepository.save(feedbackEntity);
        } else {
            throw new IllegalArgumentException("Feedback cannot be null");
        }
    }

}
