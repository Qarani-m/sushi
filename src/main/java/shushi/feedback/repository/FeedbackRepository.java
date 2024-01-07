package shushi.feedback.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import shushi.feedback.entity.FeedbackEntity;

import java.util.List;


@Repository
public interface FeedbackRepository extends MongoRepository<FeedbackEntity, String> {
    List<FeedbackEntity> getFeedbackBySushiId(String shushiId);

}
