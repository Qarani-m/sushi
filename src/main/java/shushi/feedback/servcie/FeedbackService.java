package shushi.feedback.servcie;

import shushi.feedback.dto.FeedbackDto;
import shushi.feedback.entity.FeedbackEntity;

import java.util.List;
import java.util.Map;

public interface FeedbackService {
    Map<String, Object>  getAllFeedback(String productId);

    Map<String, Object> getFeedbackById(String id);

    Map<String, Object>  createFeedback(FeedbackDto feedback);

}
