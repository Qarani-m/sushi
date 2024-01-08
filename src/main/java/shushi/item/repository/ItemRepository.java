package shushi.item.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import shushi.item.entity.ItemEntity;

import java.util.List;

public interface ItemRepository extends MongoRepository<ItemEntity, String> {
    List<ItemEntity> findByPriceBetweenAndCategoryAndStars(double minPrice, double maxPrice, String category, int stars);

}
