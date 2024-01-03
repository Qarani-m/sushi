package shushi.sushi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import shushi.sushi.entity.SushiEntity;

import java.util.List;

public interface SushiRepository extends MongoRepository<SushiEntity , String> {
    // Custom query to filter sushi items by price range, category, and stars
    List<SushiEntity> findByPriceBetweenAndCategoryAndStars(double minPrice, double maxPrice, String category, int stars);

}
