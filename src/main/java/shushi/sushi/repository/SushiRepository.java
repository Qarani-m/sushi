package shushi.sushi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import shushi.sushi.entity.SushiEntity;

import java.util.List;

public interface SushiRepository extends MongoRepository<SushiEntity , String> {
    List<SushiEntity> findByPriceBetweenAndCategoryAndStars(double minPrice, double maxPrice, String category, int stars);

}
