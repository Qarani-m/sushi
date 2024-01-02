package shushi.feedback.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import shushi.feedback.entity.FeedbackEntity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackDto {
    private String userId;
    private int rating;
    private String shushiId;
    private String comment;
}
