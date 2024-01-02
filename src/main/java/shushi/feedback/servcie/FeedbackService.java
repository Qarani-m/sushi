package shushi.feedback.servcie;

import shushi.feedback.dto.FeedbackDto;
import shushi.feedback.entity.FeedbackEntity;

import java.util.List;

public interface FeedbackService {
    List<FeedbackEntity> getAllFeedback(String productId);

    FeedbackEntity getFeedbackById(String id);

    FeedbackEntity createFeedback(FeedbackDto feedback);

}
