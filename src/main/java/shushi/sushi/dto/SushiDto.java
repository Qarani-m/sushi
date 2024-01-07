package shushi.sushi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SushiDto {
    private String name;
    private double price;
    private String description;
    private int stars;
    private String category;
    private String imageUrl;
}
