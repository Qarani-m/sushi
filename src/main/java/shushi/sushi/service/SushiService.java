package shushi.sushi.service;

import shushi.sushi.entity.SushiEntity;

import java.util.List;
import java.util.Optional;

public interface SushiService {
    List<SushiEntity> findAll();

    Optional<SushiEntity> getSushiById(String sushiId);

    Optional<SushiEntity> updateSushi(String sushiId, SushiEntity updatedSushi);

    SushiEntity createSushi(SushiEntity sushiEntity);

    boolean deleteSushi(String sushiId);

    List<SushiEntity> filterSushi(Double minPrice, Double maxPrice, String category, Integer stars);
}
