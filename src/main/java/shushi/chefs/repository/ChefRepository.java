package shushi.chefs.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import shushi.chefs.entity.ChefEntity;

import java.util.List;
import java.util.Optional;

public interface ChefRepository extends MongoRepository<ChefEntity, String> {

    Optional<ChefEntity> findByName(String name);
    ChefEntity findByTopChefIsTrue();


}
