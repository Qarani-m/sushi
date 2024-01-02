package shushi.sushi.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "sushi")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SushiEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String name;
    private double price;
    private String description;
    private int stars;
    private String category;
    private String imageUrl;

}
